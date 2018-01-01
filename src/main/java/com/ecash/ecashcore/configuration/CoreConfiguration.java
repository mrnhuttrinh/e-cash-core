package com.ecash.ecashcore.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreConfiguration
{
  @Bean
  public ModelMapper modelMapper()
  {
    return new ModelMapper();
  }

}
