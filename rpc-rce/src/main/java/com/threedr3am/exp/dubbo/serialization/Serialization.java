package com.threedr3am.exp.dubbo.serialization;

import com.threedr3am.exp.dubbo.payload.Payload;

import java.io.IOException;

/**
 * @author threedr3am
 */
public interface Serialization {

  byte[] makeData(Payload payload, String[] args, String protocol) throws Exception;

  byte getType();

  void setPayload(Payload payload);

  Payload getPayload();

}
