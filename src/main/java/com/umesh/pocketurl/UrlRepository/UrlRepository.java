package com.umesh.pocketurl.UrlRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umesh.pocketurl.Entity.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long>{
	
		Optional<Url> findByLongUrl(String longUrl);
		Optional<Url> findByShortUrl(String shortUrl);
}
