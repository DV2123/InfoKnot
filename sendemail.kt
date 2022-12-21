package com.example.wholeprojectfiles

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class sendemail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sendemail)

        var sendEmailBtn = findViewById<Button>(R.id.sendEmailBtn)
        var subjectEt = findViewById<EditText>(R.id.subjectEt)
        var messageEt = findViewById<EditText>(R.id.messageEt)

//button click to get input and call sendEmail method
        sendEmailBtn.setOnClickListener {
            if(subjectEt.text.isEmpty() || messageEt.text.isEmpty())
            {
                Toast.makeText(this,"Please Fill Up All Details",Toast.LENGTH_SHORT).show()
            }
            else
            {
                //get input from EditTexts and save in variables

                val subject: String = subjectEt.text.toString().trim()
                val message: String = messageEt.text.toString().trim()

                //method call for email intent with these inputs as parameters
                sendEmail(subject,message)
                subjectEt.text=null
                messageEt.text=null
            }
        }
    } // END OF MAIN METHOD

   private fun sendEmail(subject: String, message: String) {
        /*ACTION_SEND action to launch an email client installed on your Android device.*/
       val recipient:String ="TeamWireVe@gmail.com"
        val mIntent = Intent(Intent.ACTION_SEND)
        /*To send an email you need to specify mailto: as URI using setData() method
        and data type will be to text/plain using setType() method*/
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        // put recipient email in intent
        /* recipient is put as array because you may wanna send email to multiple emails
           so enter comma(,) separated emails, it will be stored in array*/
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        //put the Subject in the intent
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        //put the message in the intent
        mIntent.putExtra(Intent.EXTRA_TEXT, message)

        try {
            //start email intent
            startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
        }
        catch (e: Exception){
            //if any thing goes wrong for example no email client application or any exception
            //get and show exception message
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }

    }


} // END OF CLASS