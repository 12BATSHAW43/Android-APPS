# Android-CALC-APP


![calc](https://github.com/ManthanGajjar12/Android-CALC-APP/assets/96676537/7a905f32-fdc1-478f-8a5b-821df3b3ae75)



NOTE this design layout is not dynamic i.e it doesn't support every device screen aspect ratios.
And this code is written using JAVA in Android Studio

Don't forget to implement RHINO open source library which is used to evalutate the data string


USED nested linear layout to create button layount for the calculator app in which each linear layout consists of 4 material button components 



We created  void assignId(MaterailButton btn, int id) method to assign id and setOnClickListener to respective button using this method. and for this we need to implement View.OnClickListener interface
. We will override onClick() method , in this method we will get the button and get the text from that button and set it  into solutiontv .
In solution text view we are going to display whole operations user clicked and if he/she clicks equalsto then we set solution =result text 
We set Functionality of buttons AC ,= ,^and C differently using if block in onClick method 
This libraries is used to evaluate the calcuation of the string and  set it into solution text view and result textview when equals_to is pressed --->


---->implementation("com.faendir.rhino:rhino-android:1.5.2")
This is called RHINO which is an open source implementation of javascript written entirely in java


We import 
    -->import org.mozilla.javascript.Context;
            import org.mozilla.javascript.Scriptable;
this two pacakges 

 String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        } catch (Exception e) {
            return "Err";
        }
    }//end of getResult
