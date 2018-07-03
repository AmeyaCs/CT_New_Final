import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonFileModification {
    static int modifyFile(String filePath)  // , String oldString, String newString)
    {
        File fileToBeModified = new File(filePath);
        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;

        try
        {
            reader = new BufferedReader(new FileReader(fileToBeModified));

            //Reading all the lines of input text file into oldContent
            String line = reader.readLine();

            while (line != null)
            {
                line=line.replaceAll("\\s","");
                if(line.startsWith("\"lhs\":{"))
                    oldContent = oldContent + line + System.lineSeparator();
                else if(line.startsWith("\"lhs\"")){

                    line=line.replace("\"lhs\"","\"lhsf\"");
                    oldContent = oldContent + line + System.lineSeparator();
                }
                else
                    oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }


            //Rewriting the input text file with newContent

            writer = new FileWriter(fileToBeModified);
            writer.write(oldContent);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                //Closing the resources
                reader.close();
                writer.close();
                return 2;
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return 1;
    }
}
