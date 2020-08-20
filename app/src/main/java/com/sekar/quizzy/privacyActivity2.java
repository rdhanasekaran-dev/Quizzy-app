package com.sekar.quizzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class privacyActivity2 extends AppCompatActivity {
    TextView privacyPolicy,infoColUse,googlePrivacy,logdata,cooky,security,links,child,change,contact,service;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy2);
        security=findViewById(R.id.secure);
        security.setText("                    We value your trust in providing us your Personal Information, thus we are striving to use commercially acceptable means of protecting it. But remember that no method of transmission over the internet, or method of electronic storage is 100% secure and reliable, and we cannot guarantee its absolute security.");
        links=findViewById(R.id.links);
        links.setText("                    This Service may contain links to other sites. If you click on a third-party link, you will be directed to that site. Note that these external sites are not operated by me. Therefore, our strongly advise you to review the Privacy Policy of these websites. We have no control over and assume no responsibility for the content, privacy policies, or practices of any third-party sites or services.");
        child=findViewById(R.id.child);
        child.setText("                    These Services do not address anyone under the age of 13. We do not knowingly collect personally identifiable information from children under 13. In the case we discover that a child under 13 has provided me with personal information, we immediately delete this from our servers. If you are a parent or guardian and you are aware that your child has provided us with personal information, please contact us so that we will be able to do necessary actions.");
        change=findViewById(R.id.change);
        change.setText("                    We may update our Privacy Policy from time to time. Thus, you are advised to review this page periodically for any changes. We will notify you of any changes by posting the new Privacy Policy on this page.This policy is effective as of 2030-01-01");
        contact=findViewById(R.id.contact);
        contact.setText("                    If you have any questions or suggestions about our Privacy Policy, do not hesitate to contact us at dogoodteamofdevelopers@gmail.com.");
        service=findViewById(R.id.service);
        service.setText("                    We may employ third-party companies and individuals due to the following reasons:\n" +
                "\n" +
                "To facilitate our Service.\n" +
                "To provide the Service on our behalf.\n" +
                "To perform Service-related services.\n" +
                "To assist us in analyzing how our Service is used.\n\n" +
                "                    We want to inform users of this Service that these third parties have access to your Personal Information. The reason is to perform the tasks assigned to them on our behalf. However, they are obligated not to disclose or use the information for any other purpose.");
        logdata=findViewById(R.id.logdata);
        logdata.setText("                    We want to inform you that whenever you use my Service, in a case of an error in the app we collect data and information (through third party products) on your phone called Log Data. This Log Data may include information such as your device Internet Protocol (“IP”) address, device name, operating system version, the configuration of the app when utilizing my Service, the time and date of your use of the Service, and other statistics.");
        cooky=findViewById(R.id.cookies);
        cooky.setText("                    Cookies are files with a small amount of data that are commonly used as anonymous unique identifiers. These are sent to your browser from the websites that you visit and are stored on your device's internal memory.This Service does not use these “cookies” explicitly. However, the app may use third party code and libraries that use “cookies” to collect information and improve their services. You have the option to either accept or refuse these cookies and know when a cookie is being sent to your device. If you choose to refuse our cookies, you may not be able to use some portions of this Service.");
        privacyPolicy=findViewById(R.id.privacyPolicy);
        privacyPolicy.setText("                    DoGoodTeam built the Quizzy app as a Free app. This SERVICE is provided by DoGoodTeam at no cost and is intended for use as is. This page is used to inform visitors regarding our policies with the collection, use, and disclosure of Personal Information if anyone decided to use my Service. If you choose to use my Service, then you agree to the collection and use of information in relation to this policy. The Personal Information that we collect is used for providing and improving the Service. we will not use or share your information with anyone except as described in this Privacy Policy. The terms used in this Privacy Policy have the same meanings as in our Terms and Conditions, which is accessible at Quizzy unless otherwise defined in this Privacy Policy.");
        infoColUse=findViewById(R.id.infoCollAndUse);
        infoColUse.setText("                    For a better experience, while using our Service, we may require you to provide us with certain personally identifiable information, including but not limited to username,gmail account,user password. The information that we request will be retained on your device and is not collected by me in any way. The app does use third party services that may collect information used to identify you. Link to privacy policy of third party service providers used by the app");
        googlePrivacy=findViewById(R.id.googlePlay);
        googlePrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchGooglePlay=new Intent(Intent.ACTION_VIEW, Uri.parse("https://policies.google.com/privacy"));
                startActivity(launchGooglePlay);
            }
        });

        b1=findViewById(R.id.ok);
        b2=findViewById(R.id.questions);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String[] TO = {"dogoodteamofdevelopers@gmail.com"};
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setData(Uri.parse("mailto:"));
                    emailIntent.setType("text/plain");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Quizzy customer Questions");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Questions on Privacy Policy");
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));

                }catch (ActivityNotFoundException ex){

                };
            }
        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}