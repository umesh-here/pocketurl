package com.umesh.pocketurl.Entity;


import com.umesh.pocketurl.Service.UniqueIdGenerator;

import jakarta.persistence.Entity;
//import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
//import java.net.URL; 
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Size;

@Entity
public class Url {
	
	@Id
	@GeneratedValue
	private Long Id;
	
	@Size(max = 1000, message = "URL is too long. Max length is 1000 characters.")
	private String longUrl;
	
	private String shortUrl;
	
	@PrePersist
    private void generateUniqueCode() {
        if (this.shortUrl == null || this.shortUrl.isEmpty()) {
            this.shortUrl = UniqueIdGenerator.generateUniqueId();
        }
    }

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}


	
	
}
