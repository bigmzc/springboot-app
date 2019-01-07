package com.baizhi.conf;

import com.baizhi.luceneservice.LuceneAlbumService;
import com.baizhi.luceneservice.LuceneAlbumServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LuceneConfig {
    @Bean
    public LuceneAlbumService getLuceneAlbumServiceImpl() {
        return new LuceneAlbumServiceImpl();
    }
}
