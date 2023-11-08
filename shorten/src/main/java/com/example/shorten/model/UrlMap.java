package com.example.shorten.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "t_url_map", indexes = {
        @Index(name = "idx_long_url", columnList = "longUrl", unique = true),
        @Index(name = "idx_expire_time", columnList = "expireTime", unique = false)
})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UrlMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String longUrl;

    private Date expireTime;

    @CreationTimestamp
    private Date creationTime;
}