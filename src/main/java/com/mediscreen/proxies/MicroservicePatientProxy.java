package com.mediscreen.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "mediscreen-patient", url = "http://localhost:9010")
public interface MicroservicePatientProxy {

  @GetMapping("/patientExists")
  boolean doesPatientExist(@RequestParam("id") Integer patientId);

}
