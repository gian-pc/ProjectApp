package com.carato.config;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class S3Configuration {
    @Value("${spring.s3.access.key}")
    private String accessKey;

    @Value("${spring.s3.secret.key}")
    private String secreteKey;

    @Value("${spring.s3.bucket.name}")
    private String bucketName;

    @Bean
    public BasicAWSCredentials getCredentials(){
        return new BasicAWSCredentials(accessKey,secreteKey);
    }

    @Bean
    public AmazonS3 getClient(){
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(this.getCredentials()))
                .withRegion(Regions.US_EAST_1)
                .build();
    }
}
