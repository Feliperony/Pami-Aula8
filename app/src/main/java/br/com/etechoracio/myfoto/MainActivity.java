package br.com.etechoracio.myfoto;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onCapturar(View view){
        File diretorio = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String nomeImagem  = diretorio.getPath() + "/" + System.currentTimeMillis() + ".jpg";
        uri = Uri.fromFile(new File(nomeImagem));

        Intent intent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, 777);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == 777 && resultCode == RESULT_OK){
            Toast.makeText(this, "Imagem Capturada!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            intent.setData(uri);
            this.sendBroadcast(intent);
        }else{
            Toast.makeText(this, "Imagem Não Capturada!", Toast.LENGTH_LONG).show();
        }

    }

    public void onVisualizar(View view){

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri,"image/jpeg");
        startActivity(intent);

    }
}
