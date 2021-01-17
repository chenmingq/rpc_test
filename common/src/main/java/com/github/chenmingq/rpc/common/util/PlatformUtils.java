package com.github.chenmingq.rpc.common.util;

/**
 * @author : cmq
 * date : 2021/01/
 * description :
 */

public class PlatformUtils {


    /**
     * 操作系统
     */
    public enum OsType {
        /**
         * linux
         */
        LINUX_OS,

        /**
         * windows
         */
        WINDOWS_OS,

        /**
         * mac
         */
        MAC_OS,
        ;

    }


    private static OsType osType;

    static {
        String osName = System.getProperty("os.name");
        if (osName != null) {
            String os = osName.toLowerCase();
            if (os.contains("linux")) {
                osType = OsType.LINUX_OS;
            } else if (os.contains("windows")) {
                osType = OsType.WINDOWS_OS;
            } else if (os.contains("mac")) {
                osType = OsType.MAC_OS;
            }
        }
    }

    public static OsType os() {
        return osType;
    }

}
