package aiv.ashivered.book.alphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button searchButton;
    private EditText nameForSearch;
    private ListView alphoneList;
    private List<String> subAlphone;

    private static final int MAX_NAME_LEN = 45;
    private String[] allAlphoneList = {
/* add your contacs here */
            "johan dao 0550000000",
            "israel israeli 0551111111",
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchButton = findViewById(R.id.search_button);
        nameForSearch = findViewById(R.id.search_edittext);
        alphoneList = findViewById(R.id.alphone_list);

        subAlphone = new ArrayList<>();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genAlphone();
            }
        });

        genSearchScr();
    }

    private void genAlphone(){
        String nameToSearch = nameForSearch.getText().toString();
        subAlphone.clear();
        for(int i=0;i<allAlphoneList.length;++i)
        {
            String s = allAlphoneList[i];
            if(s.contains(nameToSearch)){
                subAlphone.add(s);
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, subAlphone);
        alphoneList.setAdapter(adapter);
        alphoneList.setOnItemClickListener((parent, view, position, id) -> {
            Dial(position);
        });
    }

    private void genSearchScr() {
        nameForSearch.setText("");
        subAlphone.clear();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, subAlphone);
        alphoneList.setAdapter(adapter);
    }

    private void Dial(int position){
        String call = subAlphone.get(position);
        call = call.substring(call.indexOf('0'));
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + call));
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
