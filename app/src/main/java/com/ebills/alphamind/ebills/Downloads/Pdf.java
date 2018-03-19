package com.ebills.alphamind.ebills.Downloads;

import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by anmol on 19/3/18.
 */

public class Pdf {

    Context ctx;

    // Download Pdf
    public Pdf(Context ctx){
        this.ctx = ctx;
    }

    // download Pdf
    public void downloadPdf(String href , String name){

        File direct = new File(Environment.getExternalStorageDirectory()
                + "/EBills");

        if (!direct.exists()) {
            direct.mkdirs();
        }

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(href));
        request.setTitle(name);
        request.setDescription("Downloading.....");
        //   request.setMimeType("application/jpeg");
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        final String filename = URLUtil.guessFileName(href, null, MimeTypeMap.getFileExtensionFromUrl(href));
        //      request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,filename);
        request.setDestinationInExternalPublicDir("/EBills", filename);
        DownloadManager manager = (DownloadManager)ctx.getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);

        BroadcastReceiver onComplete = null;
        onComplete = new BroadcastReceiver() {
            public void onReceive(Context ctxt, Intent intent) {

                File[] listFile;
                ArrayList<String> f = new ArrayList<>();
                File file = new File(Environment.getExternalStorageDirectory() + "/Ebills");  // -> filename = maven.pdf
                if (file.isDirectory()) {
                    listFile = file.listFiles();


                    for (int i = 0; i < listFile.length; i++) {

                        if (listFile[i].getName().equals(filename)) {
                            f.add(listFile[i].getAbsolutePath());
                        }

                    }


                }

                Uri path = Uri.fromFile(new File(f.get(0)));
                Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                pdfIntent.setDataAndType(path, "application/pdf");
                pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                try{
                    ctx.startActivity(pdfIntent);
                }catch(ActivityNotFoundException e){
                    Toast.makeText(ctx, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
                }


            }
        };

        ctx.registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

}
