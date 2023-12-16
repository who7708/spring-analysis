package org.spring.boot.test.base.slicedownload;

import lombok.Data;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 分片页信息
 */
@Data
public class SlicePageInfo {

    private CopyOnWriteArrayList<SliceInfo> sliceInfoList;

    private Long page;
}