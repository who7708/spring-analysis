package org.spring.boot.test.base.slicedownload;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 文件分片信息
 */
@Data
@AllArgsConstructor
public class SliceInfo {
    private long start;
    private long end;
    private long page;
}