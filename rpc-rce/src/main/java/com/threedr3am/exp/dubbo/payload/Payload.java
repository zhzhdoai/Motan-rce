package com.threedr3am.exp.dubbo.payload;

import java.util.Map;

/**
 * @author threedr3am
 */
public interface Payload {

  Object getPayload(String[] args) throws Exception;

  default PackageType getPackageType() {
    return PackageType.EVENT;
  }

  void injectDefaultArgs(String[] args);

}
