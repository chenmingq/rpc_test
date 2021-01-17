package com.github.chenmingq.rpc.common.constant;

import java.io.File;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

public interface RoteConst {

    /**
     * 拆包唯一标识
     */
    interface CoderMagicConst {
        /**
         * 注册中心
         */
        int CODER_UNIQUE_MAGIC = 0X3e8;
    }

    interface FileConst {
        /**
         * 写入地址的文件后缀
         */
        String ADDRESS_FILE_SUFFIX = ".tt";

        /**
         * 根目录
         */
        String FILE_USER_DIR = System.getProperty("user.dir") + File.separator;
    }

}
