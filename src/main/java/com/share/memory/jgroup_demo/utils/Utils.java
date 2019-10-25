package com.share.memory.jgroup_demo.utils;

import org.jgroups.conf.ConfiguratorFactory;
import org.jgroups.conf.ProtocolConfiguration;
import org.jgroups.conf.ProtocolStackConfigurator;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * author : fanzhe
 * email : fansy1990@foxmail.com
 * date : 2019/10/25 AM8:19.
 */
public class Utils {

    public static ProtocolStackConfigurator setLocalIp(String file) throws Exception {
        ProtocolStackConfigurator protocolConfiguration =
                ConfiguratorFactory.getStackConfigurator(file);

        return transform(protocolConfiguration);
    }

    public static ProtocolStackConfigurator transform(ProtocolStackConfigurator protocolStackConfigurator) throws UnknownHostException {
        List<ProtocolConfiguration> list =protocolStackConfigurator.getProtocolStack();
        for(ProtocolConfiguration l: list){
            System.out.println();
            if("TCP".equals(l.getProtocolName())){
                l.getProperties().put("bind_addr", InetAddress.getLocalHost().getHostAddress());
            }
        }
        return protocolStackConfigurator;
    }

}
