package com.foodject.restapi;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class NaverObj {

	@Value("${NaverAccessKey}")
	String accessKey;

	@Value("${NaverSecretKey}")
	String secretKey;

	final String endPoint = "https://kr.object.ncloudstorage.com";
	final String regionName = "kr-standard";
	final String bucketName = "foodject";


	// mf = 실제 파일, name = 파일이름.jpg, table = 폴더명(db테이블명)
	public void upLoad(MultipartFile mf, String filePath, String name, String table) {

		// S3 client
		final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
				.build();
		

		System.out.println("filePath : " + filePath);
		// upload local file
		// String objectName = "cssimg/test1.jpg";
		String objectName = table + "/" + name;
		// String userpath = Paths.get(System.getProperty("user.dir"), "src",
		// "main","resources","static","foodject", table ).toString();
		// String filePath = "C:/Users/b4a41/Pictures/ma.jpg";
		// String filePath = userpath+name;

		try {
			// s3.putObject(bucketName, objectName, new File(filePath));
			s3.putObject(new PutObjectRequest(bucketName, objectName, new File(filePath)).withCannedAcl(CannedAccessControlList.PublicRead));
			System.out.format("Object %s has been created.\n", objectName);
		} catch (AmazonS3Exception e) {
			e.printStackTrace();
		} catch (SdkClientException e) {
			e.printStackTrace();
		}

	}

	// table = 오브젝트 스토리지의 폴더명, img = 파일 명.jpg
	public void downLoad(String table, String img) throws IOException {
		int cnt = 0;
		// S3 client
		final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
				.build();

		
		String objectName = table + "/" + img;
		// list all in the bucket
		try {
			ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
					.withBucketName(bucketName)
					.withMaxKeys(300).withPrefix(objectName);

			ObjectListing objectListing = s3.listObjects(listObjectsRequest);

			System.out.println("Object List:");
			while (true) {
				for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
					System.out.println("    name=" + objectSummary.getKey() + ", size=" + objectSummary.getSize()
							+ ", owner=" + objectSummary.getOwner().getId());
					cnt++;
				}

				if (objectListing.isTruncated()) {
					objectListing = s3.listNextBatchOfObjects(objectListing);
				} else {
					break;
				}
			}
			
		} catch (AmazonS3Exception e) {
			System.err.println(e.getErrorMessage());
			System.exit(1);
		}

		if (cnt <= 0) {

			String userpath = Paths
					.get(System.getProperty("user.dir"), "src", "main", "resources", "static", "foodject", table)
					.toString();
			System.out.println("useerpath : " + userpath);


			// String objectName = "cust/moon1.jpg";

			String downloadFilePath = userpath + "/" + img;

			// download object
			try {
				System.out.println("S3Object upper");
				S3Object s3Object = s3.getObject(bucketName, objectName);
				System.out.println("S3ObjectInputStream upper");
				S3ObjectInputStream s3ObjectInputStream = s3Object.getObjectContent();

				OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFilePath));
				byte[] bytesArray = new byte[4096];
				int bytesRead = -1;
				while ((bytesRead = s3ObjectInputStream.read(bytesArray)) != -1) {
					outputStream.write(bytesArray, 0, bytesRead);
				}

				outputStream.close();
				s3ObjectInputStream.close();
				System.out.format("Object %s has been downloaded.\n", objectName);
			} catch (AmazonS3Exception e) {
				e.printStackTrace();
			} catch (SdkClientException e) {
				e.printStackTrace();
			}

		}

	}

}
