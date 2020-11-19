package com.FacultyRecord;

import android.app.AlertDialog;
import android.database.Cursor;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    EditText name,address,highestdegree,idnumber;
	DatabaseConnection myDb;
    Button Adding,Viewing,Deleting,Updating;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        myDb = new DatabaseConnection(this);

        name = (EditText)findViewById(R.id.name);
        address = (EditText)findViewById(R.id.address);
        highestdegree = (EditText)findViewById(R.id.degree);
        idnumber = (EditText)findViewById(R.id.id);

		//button

        Adding = (Button)findViewById(R.id.add);
        Viewing = (Button)findViewById(R.id.viewAll);
        Updating = (Button)findViewById(R.id.update);
        Deleting = (Button)findViewById(R.id.delete);

        AddData();
        ViewData();
        UpdateData();
        DeleteData();
    }

	
	
	
	//ADDING THE DATA


    public  void AddData() {
        Adding.setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					boolean isInserted = myDb.insertData(idnumber.getText().toString(),
					                                     name.getText().toString(),
														 address.getText().toString(),
														 highestdegree.getText().toString() );
					if(isInserted == true){
						Toast.makeText(MainActivity.this,"Inserted",Toast.LENGTH_LONG).show();
						idnumber.setText("");
						name.setText("");
						address.setText("");
						highestdegree.setText("");
					}
					else
						Toast.makeText(MainActivity.this,"Not Inserted",Toast.LENGTH_LONG).show();
				}
			}
        );
    }
	
	
	
	


	//DELETING THE DATA
    public void DeleteData() {
        Deleting.setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Integer deletedRows = myDb.deleteData(idnumber.getText().toString());
					if(deletedRows > 0){
						Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
						idnumber.setText("");

					}
					else
						Toast.makeText(MainActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();
				}
			}
        );
    }


	//UPDATING THE  DATA

    public void UpdateData() {
        Updating.setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					boolean isUpdate = myDb.updateData(idnumber.getText().toString(),
													   name.getText().toString(),
													   address.getText().toString(),
													   highestdegree.getText().toString());
					if(isUpdate == true){
						Toast.makeText(MainActivity.this,"Data Update",Toast.LENGTH_LONG).show();
						idnumber.setText("");
						name.setText("");
						address.setText("");
						highestdegree.setText("");
					}
					else
						Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
				}
			}
        );
    }



	


	//VIEWING THE  DATA

    public void ViewData() {
        Viewing.setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Cursor res = myDb.getAllData();
					if(res.getCount() == 0) {

						showMessage("Error","Nothing found");
						return;
					}




					
					

					//Result

					StringBuffer buffer = new StringBuffer();

					while (res.moveToNext()) {
						buffer.append("Id Number :"+ res.getString(0)+"\n");
						buffer.append("Name :"+ res.getString(1)+"\n");
						buffer.append("Address :"+ res.getString(2)+"\n");
						buffer.append("Highest Degree :"+ res.getString(3)+"\n\n");
					}
					showMessage("RESULT",buffer.toString());
				}
			}
        );
    }



    public void showMessage(String title,String Message){
        AlertDialog.Builder faculty = new AlertDialog.Builder(this);
        faculty.setCancelable(true);
        faculty.setTitle(title);
        faculty.setMessage(Message);
        faculty.show();

    }
}
