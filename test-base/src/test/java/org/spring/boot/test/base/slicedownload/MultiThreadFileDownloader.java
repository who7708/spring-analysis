// package org.spring.boot.test.base.切片下载;
//
// import org.apache.commons.io.FileUtils;
// import org.apache.commons.lang3.StringUtils;
//
// import java.io.File;
// import java.io.IOException;
// import java.io.InputStream;
// import java.io.RandomAccessFile;
// import java.net.HttpURLConnection;
// import java.net.URL;
// import java.security.KeyManagementException;
// import java.security.KeyStoreException;
// import java.security.NoSuchAlgorithmException;
// import java.util.concurrent.CountDownLatch;
//
// public class MultiThreadFileDownloader {
//
//     // 第个分片大小 1M
//     private static final int sizeOfSlice = 1024 * 1024;
//
//     private final String fileUrl;
//     private final String storageDir;
//     private final int threadNumber;
//     private static long downloadByteCount;
//
//     MultiThreadFileDownloader(String fileUrl, String storageDir, int threadNumber) {
//         this.fileUrl = fileUrl;
//         this.storageDir = storageDir;
//         this.threadNumber = threadNumber;
//     }
//
//     /**
//      * 多线程基于文件下载
//      */
//     public void download() throws IOException, InterruptedException {
//         System.out.println("开始下载。。。");
//         long startTime = System.currentTimeMillis();
//         // 先请求文件，得到文件总大小
//         long fileLength = getFileLength();
//         System.out.println("fileLength = " + fileLength);
//         // 获取下载的文件名
//         String filename = getFilename();
//         System.out.println("filename = " + filename);
//
//         // 先组装分片列表
//         final String sliceFormat = "bytes=%d-%d";
//         // 第个线程负责下载的大小
//         long sizeOfPerThread = fileLength / threadNumber;
//         System.out.println("sizeOfPerThread = " + sizeOfPerThread);
//         CountDownLatch countDownLatch = new CountDownLatch(threadNumber);
//         for (int i = 0; i < threadNumber; i++) {
//             long startPosition = i * sizeOfPerThread;
//             long endPosition = Math.min((i + 1) * sizeOfPerThread - 1, fileLength);
//             File tempFile = new File(storageDir + File.separator + filename + "." + i);
//             final String slice = String.format(sliceFormat, startPosition, endPosition);
//             System.out.println("slice = " + slice);
//             // CompletableFuture.runAsync(new DownloadTask(tempFile, startPosition, endPosition, countDownLatch));
//         }
//         countDownLatch.await();
//         System.out.println("分片下载完成");
//
//         RandomAccessFile file = new RandomAccessFile(storageDir, "rwd");
//         // 设置本地文件长度
//         file.setLength(fileLength);
//         file.close();
//         // CompletableFuture.runAsync(() -> {
//         //     long temp = 0;
//         //     long speed;
//         //     while (downloadByteCount < fileLength) {
//         //         speed = downloadByteCount - temp;
//         //         temp = downloadByteCount;
//         //         System.out.println("文件总大小： " + fileLength / 1000 + " KB，已下载：" + (downloadByteCount / 1024) + "KB，下载速度：" + (speed / 1000) + "KB/S");
//         //         try {
//         //             Thread.sleep(1000);
//         //         } catch (InterruptedException e) {
//         //             throw new RuntimeException(e);
//         //         }
//         //     }
//         // });
//         // /*
//         //  *  计算每条线程下载的字节数，以及每条线程起始下载位置与结束的下载位置，
//         //  *  因为不一定平均分，所以最后一条线程下载剩余的字节
//         //  *  然后创建线程任务并启动
//         //  *  Main线程等待每条线程结束(join()方法)
//         //  */
//         // long oneThreadReadByteLength = fileLength / threadNumber;
//         // CountDownLatch countDownLatch = new CountDownLatch(threadNumber);
//         // for (int i = 0; i < threadNumber; i++) {
//         //     long startPosition = i * oneThreadReadByteLength;
//         //     long endPosition = i == threadNumber - 1 ? fileLength : (i + 1) * oneThreadReadByteLength - 1;
//         //     Thread t = new Thread(new DownloadTask(startPosition, endPosition, countDownLatch));
//         //     t.start();
//         // }
//         // countDownLatch.await();
//         // /*
//         //  *  检查文件是否下载完整，不完整则删除
//         //  */
//         // if (downloadByteCount == fileLength) {
//         //     System.out.println("下载完毕！总耗时长" + (System.currentTimeMillis() - startTime) / 1000 + "s！");
//         // } else {
//         //     System.out.println("下载错误！");
//         //     new File(storageDir).delete();
//         // }
//     }
//
//     private String getFilename() {
//         return StringUtils.substringAfterLast(fileUrl, File.separator);
//     }
//
//     private long getFileLength() throws IOException {
//         URL url = new URL(fileUrl);
//         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//         conn.setConnectTimeout(10000);
//         conn.setRequestMethod("GET");
//         // 得到需要下载的文件大小
//         long fileLength = conn.getContentLengthLong();
//         conn.disconnect();
//         return fileLength;
//     }
//
//     class DownloadTask implements Runnable {
//         private final File tempFile;
//         private final long startPosition;
//         private final long endPosition;
//         private final CountDownLatch countDownLatch;
//
//         DownloadTask(File tempFile, long startPosition, long endPosition, CountDownLatch countDownLatch) {
//             this.tempFile = tempFile;
//             this.startPosition = startPosition;
//             this.endPosition = endPosition;
//             this.countDownLatch = countDownLatch;
//         }
//
//         @Override
//         public void run() {
//             try {
//                 URL url = new URL(fileUrl);
//                 HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                 conn.setConnectTimeout(10000);
//                 conn.setRequestMethod("GET");
//                 // 关键方法: 每条线程请求的字节范围
//                 conn.setRequestProperty("Range", "bytes=" + startPosition + "-" + endPosition);
//                 // 关键响应码 ：206，请求成功 + 请求数据字节范围成功
//                 if (conn.getResponseCode() == HttpURLConnection.HTTP_PARTIAL) {
//                     InputStream in = conn.getInputStream();
//                     FileUtils.copyInputStreamToFile(in, tempFile);
//                     conn.disconnect();
//                     System.out.println(Thread.currentThread().getName() + ": download OK");
//                 }
//             } catch (IOException e) {
//                 System.out.println(Thread.currentThread().getName() + "_Error : " + e);
//             } finally {
//                 countDownLatch.countDown();
//             }
//         }
//     }
//
//     public static void main(String[] args) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException, InterruptedException {
//         int processors = Runtime.getRuntime().availableProcessors();
//         System.out.println("CPU核数量: " + processors + "将开启" + processors + "个线程进行下载。。");
//         String fileURL = "https://download.oracle.com/java/21/latest/jdk-21_linux-x64_bin.tar.gz";
//         MultiThreadFileDownloader multiThreadFileDownloader = new MultiThreadFileDownloader(fileURL, "E:\\dev\\jdk\\", processors);
//         multiThreadFileDownloader.download();
//     }
//
// }
