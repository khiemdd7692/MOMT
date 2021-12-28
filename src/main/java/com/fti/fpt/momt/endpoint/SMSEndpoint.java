package com.fti.fpt.momt.endpoint;

import org.apache.log4j.Logger;
import org.tempuri.MOReceiveSoap;
import org.tempuri.MessageRequest;
import org.tempuri.MessageRequestResponse;

public class SMSEndpoint implements MOReceiveSoap {
    private static final Logger log = Logger.getLogger("MO-MT");

    @Override
    public MessageRequestResponse messageRequest(MessageRequest parameters){
        MessageRequestResponse messageRequestResponse = new MessageRequestResponse();
        Integer MOId = parameters.getMOId();
        String Telco = parameters.getTelco();
        String ServiceNum = parameters.getServiceNum();
        String Phone = parameters.getPhone();
        String Syntax = parameters.getSyntax();
        String EncryptedMessage = parameters.getEncryptedMessage();
        String Signature = parameters.getSignature();

        log.info("--------------------------");
        log.info("MOId:"+MOId +
                ", Telco:"+Telco +
                ", ServiceNum:"+ServiceNum +
                ", Phone:"+Phone +
                ", Syntax:"+Syntax +
                ", EncryptedMessage:"+EncryptedMessage +
                ", Signature:" +Signature);

        try {
            if(MOId == 0){
                messageRequestResponse.setMessageResponseResult("1|Tham so truyen vao bi loi");
                log.info("Invalid input data");
            }else {
                messageRequestResponse.setMessageResponseResult("0|Ban da dang ki thanh cong");
            }
        } catch (Exception e){
            messageRequestResponse.setMessageResponseResult("1|Da co loi xay ra");
            log.info("Exception: " + e.getMessage());
        }

        return messageRequestResponse;
    }

}
