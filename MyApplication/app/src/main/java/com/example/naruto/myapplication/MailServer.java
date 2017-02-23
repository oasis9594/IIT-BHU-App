package com.example.naruto.myapplication;

import java.util.ArrayList;
import java.util.Locale;

public class MailServer {
    public static String HasAttachments, FromDate, AttachmentSize, ToDate, Subject;
    static ArrayList<String> From, To, AttachmentType, AttachmentName, CC;
    static ArrayList<String> imageTypes=new ArrayList<>();
    static ArrayList<String> audioTypes=new ArrayList<>();
    static ArrayList<String> videoTypes=new ArrayList<>();
    static ArrayList<String> docTypes=new ArrayList<>();
    static ArrayList<String> validTypes=new ArrayList<>();
    public static void init()
    {
        imageTypes.clear();audioTypes.clear();videoTypes.clear();docTypes.clear();validTypes.clear();
        //image
        imageTypes.add("PNG");
        imageTypes.add("JPEG");
        imageTypes.add("GIF");
        imageTypes.add("JPG");
        imageTypes.add("BMP");
        imageTypes.add("TIFF");
        imageTypes.add("EXIF");
        validTypes.addAll(imageTypes);

        //documents
        docTypes.add("PDF");
        docTypes.add("XLS");
        docTypes.add("DOC");
        docTypes.add("TXT");
        docTypes.add("PPT");
        validTypes.addAll(docTypes);

        //video types
        videoTypes.add("MP4");
        videoTypes.add("AVI");
        videoTypes.add("WMV");//windows
        videoTypes.add("MOV");
        videoTypes.add("QT");//Quicktime
        videoTypes.add("MKV");
        videoTypes.add("FLV");
        videoTypes.add("OGV");
        videoTypes.add("RM");
        videoTypes.add("RMVB");
        videoTypes.add("M4V");//apple
        videoTypes.add("MPEG");//old
        videoTypes.add("MPG");//old
        videoTypes.add("SVI");//samsung
        videoTypes.add("3GP");
        videoTypes.add("AMV");
        validTypes.addAll(videoTypes);

        //audio Types
        audioTypes.add("MP3");
        audioTypes.add("M4P");//apple
        audioTypes.add("WMA");//Windows
        audioTypes.add("MSV");
        audioTypes.add("AIFF");//apple
        audioTypes.add("WAV");
        audioTypes.add("OGG");
        audioTypes.add("PCM");
        audioTypes.add("AAC");
        audioTypes.add("ALAC");//apple
        audioTypes.add("RA");//Real
        validTypes.addAll(audioTypes);

        //others
        validTypes.add("C");
        validTypes.add("CPP");
        validTypes.add("JAVA");
        validTypes.add("PY");
        validTypes.add("CSV");//contacts
        validTypes.add("VCF");//contacts
        validTypes.add("HTML");
        validTypes.add("CSS");
        validTypes.add("JS");
        validTypes.add("HTM");
        validTypes.add("PHP");
        validTypes.add("7Z");//7 zip
        validTypes.add("RAR");
        validTypes.add("ZIP");
        validTypes.add("TAR");
        validTypes.add("ISO");
        validTypes.add("DOCX");
        validTypes.add("PPTX");
        validTypes.add("XLSX");

    }
    public static boolean isMonth(String S)
    {
        return "JANUARY".equals(S)||"FEBRUARY".equals(S)||"MARCH".equals(S)||"APRIL".equals(S)||"MAY".equals(S)||"JUNE".equals(S)||"JULY".equals(S)
                ||"AUGUST".equals(S)||"SEPTEMBER".equals(S)||"OCTOBER".equals(S)||"NOVEMBER".equals(S)||"DECEMBER".equals(S)||"JAN".equals(S)
                ||"FEB".equals(S)||"MAR".equals(S)||"APR".equals(S)||"JUN".equals(S)||"JUL".equals(S)||"AUG".equals(S)||"SEPT".equals(S)
                ||"SEP".equals(S)||"OCT".equals(S)||"NOV".equals(S)||"DEC".equals(S);
    }
    public static boolean isArticle(String S)
    {
        return "A".equals(S)||"AN".equals(S)||"THE".equals(S);
    }
    public static boolean isPronoun(String S)
    {
        return "YOU".equals(S)||"HE".equals(S)||"SHE".equals(S)||"THEY".equals(S)||"IT".equals(S)||"THEM".equals(S)||"US".equals(S)||"WE".equals(S)||"HIM".equals(S)||"HER".equals(S);
    }
    public static boolean isType(String S)
    {
        if(validTypes.contains(S))return true;
        if(validTypes.contains(S.substring(0, S.length()-1))&&S.endsWith("S"))return true;
        return S.equals("DOCUMENT")||S.equals("DOCUMENTS")||S.equals("IMAGE")||S.equals("IMAGES")||S.equals("VIDEO")||S.equals("VIDEOS")||S.equals("AUDIO")
                ||S.equals("AUDIOS")||S.equals("PRESENTATIONS")||S.equals("PRESENTATION")||S.equals("EXCEL");
    }
    public static boolean check(String S)
    {
        return !("HAVING".equals(S)||isArticle(S)||"WITH".equals(S)||"WITHOUT".equals(S)||"BY".equals(S)||"SINCE".equals(S)||"IN".equals(S)||"ON".equals(S)||"AT".equals(S)||"TO".equals(S)||"FROM".equals(S)||"SUBJECT".equals(S)||Character.isDigit(S.charAt(0))
                ||"CC".equals(S)||"CC'D".equals(S)||"TYPE".equals(S)||"SIZE".equals(S)||"NAME".equals(S)||"FILE".equals(S)||"FILES".equals(S)||"ATTACHMENTS".equals(S)||"ATTACHMENT".equals(S)||"ATTACHMENTTYPE".equals(S)||"ATTACHMENTSIZE".equals(S)||"HASATTACHMENTS".equals(S)
                ||"RECEIVED".equals(S)||"SENT".equals(S)||"LAST".equals(S)||isMonth(S)||"MONTH".equals(S)||"WEEK".equals(S)||"YEAR".equals(S)||"DECADE".equals(S)||"THIS".equals(S)||isPronoun(S)||".".equals(S)||":".equals(S)||"NOT".equals(S)
                ||"TODAY".equals(S)||"WERE".equals(S)||"WHICH".equals(S)||"HAVE".equals(S)||"HAD".equals(S)||"WAS".equals(S)||"BEEN".equals(S)||"AND".equals(S)||"IN".equals(S)||"YESTERDAY".equals(S)||"MAILS".equals(S)||"MAIL".equals(S)||"DAY".equals(S)||"DATED".equals(S)||isType(S)||"NAMED".equals(S)||"ALL".equals(S)||"ANY".equals(S));
    }
    public static boolean helperfun(ArrayList<String> c)
    {
        for(String s: c)
        {
            if(AttachmentType.contains(s))return false;
        }
        for(String s: c)
            AttachmentType.add(s);
        HasAttachments="YES";
        return true;
    }
    public static void solve(String[] query)
    {
        //default
        Subject=AttachmentSize=ToDate=FromDate=HasAttachments="Any";
        From=new ArrayList<>();
        To=new ArrayList<>();
        AttachmentType=new ArrayList<>();
        AttachmentName=new ArrayList<>();
        CC=new ArrayList<>();

        ArrayList<String> S=new ArrayList<>();
        ArrayList<String> S2=new ArrayList<>();
        //parse ','  ':' etc i.e if word is ,hello separate it to , and hello
        for(int i=0;i<query.length;i++)
        {
            String x="";
            for(int j=0;j<query[i].length();j++)
            {
                char ch=query[i].charAt(j);
                if(ch==','||ch==':'||ch==';'||ch=='.'||ch=='-'||ch=='/'||ch=='<'||ch=='>'||ch=='='||ch=='?')
                {
                    if(!x.equals(""))
                    {
                        S.add(x);
                    }
                    S.add(""+ch);
                    x="";
                }
                else
                    x=x+ch;
            }
            if(!x.equals(""))
            {
                S.add(x);
            }
        }
        //convert everything to uppercase to reduce checking code
        for(int i=0;i<S.size();i++)
        {
            //System.out.print(S.get(i)+" ");
            S2.add(S.get(i));
            S.set(i, S.get(i).toUpperCase(Locale.ENGLISH));
        }
        //check From
        for(int i=0;i<S.size();i++)
        {
            int j=i;
            if("FROM".equals(S.get(i))||"BY".equals(S.get(i)))
            {
                if("BY".equals(S.get(i))&&i>0&&"RECEIVED".equals(S.get(i-1)))
                    continue;
                j++;
                while(":".equals(S.get(j)))
                    j++;
                if(Character.isDigit(S.get(j).charAt(0))||isMonth(S.get(j))||!check(S.get(j)))//from date in this case
                    continue;
                //this word must be a noun
                String x=S2.get(j);
                j++;
                while(j<S.size())
                {
                    if(",".equals(S.get(j)))
                    {
                        j++;
                        if(!"".equals(x))
                            From.add(x);
                        x="";
                        continue;
                    }
                    else if("".equals(x)&&("ME".equals(S.get(j))||"I".equals(S.get(j))))
                    {
                        x=S2.get(j);
                    }
                    else if(".".equals(S.get(j)))
                    {
                        if(j+1<S.size())
                            x+="."+S2.get(j+1);
                        j++;
                    }
                    else if(check(S.get(j)))
                    {
                        if(!"".equals(x))
                            x+=" "+S2.get(j);
                        else
                            x=S2.get(j);
                    }
                    else break;
                    j++;
                }
                if(!"".equals(x))
                    From.add(x);
                if(j<S.size()&&"AND".equals(S.get(j)))
                {
                    j++;
                    x=S2.get(j);
                    j++;
                    while(j<S.size())
                    {
                        if("".equals(x)&&("ME".equals(S.get(j))||"I".equals(S.get(j))))
                        {
                            x=S2.get(j);
                            break;
                        }
                        else if(".".equals(S.get(j)))
                        {
                            if(j+1<S.size())
                                x+="."+S2.get(j+1);
                            j++;
                        }
                        else if(check(S.get(j)))
                        {
                            x+=" "+S2.get(j);
                            j++;
                        }
                        else
                            break;
                    }
                    From.add(x);
                }
                j--;
                break;
            }
            i=j;
        }
        //All mails xyz sent to abc
        if(From.isEmpty())
        {
            for(int i=0;i<S.size();i++)
            {
                int j=i;
                if(S.get(i).equals("SENT"))
                {
                    j=i-1;
                    while(j>=0&&check(S.get(j)))
                    {
                        j--;
                    }
                    if(j==-1)
                        continue;
                    j++;
                    String x="";
                    while(j<i)
                    {
                        if(",".equals(S.get(j)))
                        {
                            j++;
                            if(!"".equals(x))
                                From.add(x);
                            x="";
                            continue;
                        }
                        else if("".equals(x)&&("ME".equals(S.get(j))||"I".equals(S.get(j))))
                        {
                            x=S2.get(j);
                        }
                        else if(".".equals(S.get(j)))
                        {
                            if(j+1<S.size())
                                x+="."+S2.get(j+1);
                            j++;
                        }
                        else if(check(S.get(j)))
                        {
                            if(!"".equals(x))
                                x+=" "+S2.get(j);
                            else
                                x=S2.get(j);
                        }
                        else break;
                        j++;
                    }
                    if(!"".equals(x))
                        From.add(x);
                    if(j<i&&"AND".equals(S.get(j)))
                    {
                        j++;
                        x=S2.get(j);
                        j++;
                        while(j<i)
                        {
                            if("".equals(x)&&("ME".equals(S.get(j))||"I".equals(S.get(j))))
                            {
                                x=S2.get(j);
                                break;
                            }
                            else if(".".equals(S.get(j)))
                            {
                                if(j+1<i)
                                    x+="."+S2.get(j+1);
                                j++;
                            }
                            else if(check(S.get(j)))
                            {
                                x+=" "+S2.get(j);j++;
                            }
                            else
                                break;
                        }
                        From.add(x);
                    }
                }
            }
        }
        //ravi's mails
        if(From.isEmpty())
        {
            for(int i=0;i<S.size();i++)
            {
                if(S.get(i).equals("MAILS")||S.get(i).equals("ATTACHMENTS")||isType(S.get(i)))
                {
                    int j=i-1;
                    if(j>=0&&S.get(j).endsWith("'S")&&check(S.get(j).substring(0, S.get(j).length()-2)))
                    {
                        From.add(S2.get(j).substring(0, S2.get(j).length()-2));
                        break;
                    }
                }
            }
        }
        //check to
        for(int i=0;i<S.size();i++)
        {
            int j=i;
            if("TO".equals(S.get(i)))
            {
                if(i>0&&(S.get(i-1).equals("CC'D")||S.get(i-1).equals("CC")))
                    continue;
                j++;
                while(":".equals(S.get(j)))
                    j++;
                if(Character.isDigit(S.get(j).charAt(0))||isMonth(S.get(j))||!check(S.get(j)))//from date in this case
                    continue;

                //this word must be a noun
                String x=S2.get(j);
                j++;
                while(j<S.size())
                {
                    if(",".equals(S.get(j)))
                    {
                        j++;
                        if(!"".equals(x))
                            To.add(x);
                        x="";
                        continue;
                    }
                    else if("".equals(x)&&("ME".equals(S.get(j))||"I".equals(S.get(j))))
                    {
                        x=S2.get(j);
                    }
                    else if(".".equals(S.get(j)))
                    {
                        if(j+1<S.size())
                            x+="."+S2.get(j+1);
                        j++;
                    }
                    else if(check(S.get(j)))
                    {
                        if(!"".equals(x))
                            x+=" "+S2.get(j);
                        else
                            x=S2.get(j);
                    }
                    else break;
                    j++;
                }
                if(!"".equals(x))
                    To.add(x);
                if(j<S.size()&&"AND".equals(S.get(j)))
                {
                    j++;
                    x=S2.get(j);
                    j++;
                    while(j<S.size())
                    {
                        if("".equals(x)&&("ME".equals(S.get(j))||"I".equals(S.get(j))))
                        {
                            x=S2.get(j);
                            break;
                        }
                        else if(".".equals(S.get(j)))
                        {
                            if(j+1<S.size())
                                x+="."+S2.get(j+1);
                            j++;
                        }
                        else if(check(S.get(j)))
                        {
                            x+=" "+S2.get(j);j++;
                        }
                        else
                            break;
                    }
                    To.add(x);
                }
                j--;
                break;
            }
            i=j;
        }
        //From Date
        //check cases like from dd/MM/yyyy and other date formats...from after since
        for(int i=0;i<S.size();i++)
        {
            int j=i;
            if("FROM".equals(S.get(i))||"AFTER".equals(S.get(i))||"SINCE".equals(S.get(i)))
            {
                j++;
                String x="";
                while(":".equals(S.get(j)))j++;
                if(!(Character.isDigit(S.get(j).charAt(0))||isMonth(S.get(j))))
                {
                    continue;
                }
                while(j<S.size())
                {
                    if(x.length()>0)
                        x+=" ";
                    x+=S2.get(j);
                    j++;
                    while(j<S.size()&&("-".equals(S.get(j))||"/".equals(S.get(j))||":".equals(S.get(j))))
                    {
                        x+=" "+S2.get(j);
                        j++;
                    }
                    //check if query[j] is month or digit
                    if(j<S.size()&&!(Character.isDigit(S.get(j).charAt(0))||isMonth(S.get(j))))
                    {
                        break;
                    }
                }

                FromDate=x;
                if("SINCE".equals(S.get(i)))
                    ToDate="Today";
                break;
            }
        }
        //To Date
        //Handles cases like to date, till date, before date
        for(int i=0;i<S.size();i++)
        {
            int j=i;
            if("TO".equals(S.get(i))||"TILL".equals(S.get(i))||"BEFORE".equals(S.get(i)))
            {
                j++;
                String x="";
                while(":".equals(S.get(j)))j++;
                if(!(Character.isDigit(S.get(j).charAt(0))||isMonth(S.get(j))))
                {
                    continue;
                }
                while(j<S.size())
                {
                    if(x.length()>0)
                        x+=" ";
                    x+=S2.get(j);
                    j++;
                    while(j<S.size()&&("-".equals(S.get(j))||"/".equals(S.get(j))||":".equals(S.get(j))))
                    {
                        x+=" "+S2.get(j);
                        j++;
                    }
                    //check if query[j] is month or digit
                    if(j<S.size()&&!(Character.isDigit(S.get(j).charAt(0))||isMonth(S.get(j))))
                    {
                        break;
                    }
                }
                ToDate=x;
                break;
            }
        }
        //Now if both FromDate and ToDate are any then check cases like between date1 and date2
        if("Any".equals(FromDate)&&"Any".equals(ToDate))
            for(int i=0;i<S.size();i++)
            {
                int j=i;
                if("BETWEEN".equals(S.get(i)))
                {
                    j++;
                    String x="";
                    while(":".equals(S.get(j)))j++;
                    if(!(Character.isDigit(S.get(j).charAt(0))||isMonth(S.get(j))))
                    {
                        continue;
                    }
                    while(j<S.size())
                    {
                        if(x.length()>0)
                            x+=" ";
                        x+=S2.get(j);
                        j++;
                        while(j<S.size()&&("-".equals(S.get(j))||"/".equals(S.get(j))||":".equals(S.get(j))))
                        {
                            x+=" "+S2.get(j);
                            j++;
                        }
                        //check if query[j] is month or digit
                        if(j<S.size()&&!(Character.isDigit(S.get(j).charAt(0))||isMonth(S.get(j))))
                        {
                            break;
                        }
                    }
                    FromDate=x;
                    j++;
                    x="";
                    while(":".equals(S.get(j)))j++;

                    while(j<S.size())
                    {
                        if(x.length()>0)
                            x+=" ";
                        x+=S2.get(j);
                        j++;
                        while(j<S.size()&&("-".equals(S.get(j))||"/".equals(S.get(j))||":".equals(S.get(j))))
                        {
                            x+=" "+S2.get(j);
                            j++;
                        }
                        //check if query[j] is month or digit
                        if(!(Character.isDigit(S.get(j).charAt(0))||isMonth(S.get(j))))
                        {
                            break;
                        }
                    }
                    ToDate=x;
                    break;
                }
            }

        //check cases like in last/past k days/weeks/quaters/months/years
        if("Any".equals(FromDate)&&"Any".equals(ToDate))
            for(int i=0;i<S.size();i++)
            {
                int j=i;
                if (S.get(i).equals("SUBJECT")) {
                    break;//as 'In' can be part of subject as well...assuming subject comes at the end
                }
                if("IN".equals(S.get(i))||"FROM".equals(S.get(i))||"FOR".equals(S.get(i)))
                {
                    j++;
                    if(j<S.size()&&"THE".equals(S.get(j)))
                        j++;
                    if(j<S.size()&&("LAST".equals(S.get(j))||"PAST".equals(S.get(j))))
                        j++;
                    else
                        continue;
                    int k=1;
                    if(Character.isDigit(S.get(j).charAt(0)))
                    {
                        k=Integer.parseInt(S.get(j));
                        j++;
                    }
                    switch(S.get(j))
                    {
                        case "HOUR":case "HOURS":case "HOUR'S":k/=24;break;
                        case "WEEK":case "WEEKS":case "WEEK'S":k*=7;break;
                        case "MONTH":case "MONTHS":case "MONTH'S":k*=30;break;
                        case "YEAR":case "YEARS":case "YEAR'S":k*=365;break;
                        case "QUARTER":case "QUARTERS":case "QUARTER'S":k*=90;break;
                    }
                    FromDate="Today";
                    ToDate="Today";
                    if(k!=0)
                        FromDate=FromDate+"-"+k;
                    break;
                }
                else if(("LAST".equals(S.get(j))||"PAST".equals(S.get(j))))
                {
                    int k=1;
                    j++;
                    if(Character.isDigit(S.get(j).charAt(0)))
                    {
                        k=Integer.parseInt(S.get(j));
                        j++;
                    }
                    switch(S.get(j))
                    {
                        case "HOUR":case "HOURS":case "HOUR'S":k/=24;break;
                        case "WEEK":case "WEEKS":case "WEEK'S":k*=7;break;
                        case "MONTH":case "MONTHS":case "MONTH'S":k*=30;break;
                        case "YEAR":case "YEARS":case "YEAR'S":k*=365;break;
                        case "QUARTER":case "QUARTERS":case "QUARTER'S":k*=90;break;
                    }
                    FromDate="Today";
                    ToDate="Today";
                    if(k!=0)
                        FromDate=FromDate+"-"+k;
                    break;
                }
            }
        //check cases like on date
        for(int i=0;i<S.size();i++)
        {
            if (S.get(i).equals("SUBJECT")) {
                break;//as 'On' can be part of subject as well...assuming subject comes at the end
            }
            int j=i;
            if("ON".equals(S.get(i)))
            {
                j++;
                String x="";
                while(":".equals(S.get(j)))j++;
                if(!(Character.isDigit(S.get(j).charAt(0))||isMonth(S.get(j))))
                {
                    continue;
                }
                while(j<S.size())
                {
                    if(x.length()>0)
                        x+=" ";
                    x+=S2.get(j);
                    j++;
                    while(j<S.size()&&("-".equals(S.get(j))||"/".equals(S.get(j))||":".equals(S.get(j))))
                    {
                        x+=S2.get(j);
                        j++;
                    }
                    //check if query[j] is month or digit
                    if(j<S.size()&&!(Character.isDigit(S.get(j).charAt(0))||isMonth(S.get(j))))
                    {
                        break;
                    }
                }
                FromDate=ToDate=x;
                break;
            }
        }
        //check cases like all mails sent to xyz by pqr today/yesterday
        if(FromDate.equals("any")&&ToDate.equals("any"))
            for (String S1 : S) {
                if (S1.equals("SUBJECT")) {
                    break;//as today can be part of subject as well...assuming subject comes at the end
                }
                if (S1.equals("TODAY")||S1.equals("TODAY'S")) {
                    FromDate=ToDate="Today";
                    break;
                }
                if (S1.equals("YESTERDAY")||S1.equals("YESTERDAY'S")) {
                    FromDate=ToDate="Today-1";
                    break;
                }
            }
        //Now check for attachments
        //first check for any attachments
        for (int i=0;i<S.size();i++) {
            if (S.get(i).equals("SUBJECT")) {
                break;
            }
            int j=i;
            if(S.get(i).equals("NOT"))
            {
                j++;
                //handle cases like not having any attachments
                if(S.get(j).equals("HAVING")){
                    j++;
                }
                else continue;
                if(S.get(j).equals("ANY"))
                    j++;
                if(S.get(j).equals("ATTACHMENTS")||S.get(j).equals("ATTACHMENT"))
                {
                    HasAttachments="NO";
                    break;
                }
                else if(S.get(j).equals("MAILS")||S.get(j).equals("MAIL"))
                {
                    j++;
                    if(S.get(j).equals("WITH")||S.get(j).equals("HAVING"))
                        j++;
                    else
                        continue;
                    if(S.get(j).equals("ATTACHMENTS")||S.get(j).equals("ATTACHMENT"))
                    {
                        HasAttachments="NO";
                        break;
                    }
                }
            }
            else if(S.get(i).equals("HAVING")||S.get(i).equals("WITH"))
            {
                j++;
                if(S.get(j).equals("ATTACHMENTS")||S.get(j).equals("ATTACHMENT"))
                {
                    HasAttachments="YES";
                    break;
                }
                if(S.get(j).equals("NO")||S.get(j).equals("0")||S.get(j).equals("ZERO"))
                    j++;
                else continue;
                if(S.get(j).equals("ATTACHMENTS")||S.get(j).equals("ATTACHMENT"))
                {
                    HasAttachments="NO";
                    break;
                }
                else if(S.get(j).equals("MAILS")||S.get(j).equals("MAIL"))
                {
                    j++;
                    if(S.get(j).equals("WITH")||S.get(j).equals("HAVING"))
                        j++;
                    else
                        continue;

                    if(S.get(j).equals("ANY"))
                        j++;
                    if(S.get(j).equals("ATTACHMENTS")||S.get(j).equals("ATTACHMENT"))
                    {
                        HasAttachments="NO";
                        break;
                    }
                }
            }
            else if(S.get(i).equals("WITHOUT"))
            {
                j++;
                if(S.get(j).equals("ANY"))
                    j++;
                if(S.get(j).equals("ATTACHMENTS")||S.get(j).equals("ATTACHMENT"))
                {
                    HasAttachments="NO";
                    break;
                }
                else if(S.get(j).equals("MAILS")||S.get(j).equals("MAIL"))
                {
                    j++;
                    if(S.get(j).equals("WITH")||S.get(j).equals("HAVING"))
                        j++;
                    else
                        continue;
                    if(S.get(j).equals("ATTACHMENTS")||S.get(j).equals("ATTACHMENT"))
                    {
                        HasAttachments="NO";
                        break;
                    }
                }
            }
        }
        if(!HasAttachments.equals("NO"))
        {
            //chcekout for attachment type
            for(int i=0;i<S.size();i++)
            {
                if(S.get(i).equals("SUBJECT"))
                    break;
                if(validTypes.contains(S.get(i)))
                {
                    if(!AttachmentType.contains(S.get(i)))
                        AttachmentType.add(S.get(i));
                    HasAttachments="YES";
                }
                else if(validTypes.contains(S.get(i).substring(0, S.get(i).length()-1))&&S.get(i).endsWith("S"))
                {
                    AttachmentType.add(S.get(i).substring(0, S.get(i).length()-1));
                    HasAttachments="YES";
                }
                else if(S.get(i).equals("DOCUMENTS")||S.get(i).equals("DOCUMENT"))
                    helperfun(docTypes);
                else if(S.get(i).equals("IMAGES")||S.get(i).equals("IMAGE"))
                    helperfun(imageTypes);
                else if(S.get(i).equals("VIDEOS")||S.get(i).equals("VIDEO")||S.get(i).equals("MOVIES")||S.get(i).equals("MOVIE"))
                    helperfun(videoTypes);
                else if(S.get(i).equals("AUDIO")||S.get(i).equals("MUSIC"))
                    helperfun(audioTypes);
                else if(S.get(i).equals("PRESENTATIONS")||S.get(i).equals("PRESENTATION"))
                {
                    if(!AttachmentType.contains("PPT"))
                        AttachmentType.add("PPT");
                    HasAttachments="YES";
                }
                else if(S.get(i).equals("SPREADSHEET")||S.get(i).equals("EXCEL"))
                {
                    if(!AttachmentType.contains("XLS"))
                        AttachmentType.add("XLS");
                    HasAttachments="YES";
                }
            }
            //check for attachment size
            for(int i=0;i<S.size();i++)
            {
                if(S.get(i).equals("SIZE"))
                {
                    int j=i+1;
                    while(j<S.size()&&!Character.isDigit(S.get(j).charAt(0)))
                        j++;
                    if(j!=S.size())
                        AttachmentSize=S2.get(j);
                }
            }
            if("Any".equals(AttachmentSize))
                for(int i=0;i<S.size();i++)
                {
                    if(S.get(i).equals("GREATER")||S.get(i).equals("LESS")||S.get(i).equals("LESSER")||S.get(i).equals("SMALLER")||S.get(i).equals("LARGER")||S.get(i).equals("BIGGER")||S.get(i).equals("OF"))
                    {
                        int j=i+1;
                        while(j<S.size()&&!Character.isDigit(S.get(j).charAt(0)))
                            j++;
                        if(j!=S.size())
                            AttachmentSize=S2.get(j);
                    }
                }
            if("Any".equals(AttachmentSize))
                for(int i=0;i<S.size();i++)
                {
                    if(S.get(i).equals("SIZE"))
                    {
                        int j=i-1;
                        while(j>=0&&!Character.isDigit(S.get(j).charAt(0)))
                            j--;
                        if(j!=-1)
                        {
                            String x="";
                            while(j<i)
                            {
                                x+=S2.get(j);
                                j++;
                            }
                            AttachmentSize=x;
                            break;
                        }
                    }
                }
            //check attachment name
            //(type) named/having name/with name
            for(int i=0;i<S.size();i++)
            {
                if(isType(S.get(i))||S.get(i).equals("ATTACHMENT")||S.get(i).equals("ATTACHMENTS")||S.get(i).equals("FIlES"))
                {
                    int j=i+1;
                    if(j<S.size()&&(S.get(j).equals("NAMED")||S.get(j).equals("ABOUT")||S.get(j).equals("ON")||S.get(j).equals("CALLED")))
                    {
                        j++;
                        int k=j;
                        while(j<S.size()&&check(S.get(j)))j++;
                        String x="";
                        boolean bb=false;
                        while(k<j)
                        {
                            if(x!="")
                            {
                                bb=true;
                                x+=" ";
                            }
                            x+=S2.get(k);
                            AttachmentName.add(S2.get(k));
                            k++;
                        }
                        if(!x.equals("")&&bb)
                            AttachmentName.add(x);
                        break;
                    }
                    else if(j<S.size()&&(S.get(j).equals("HAVING")||S.get(j).equals("WITH")))
                    {
                        j++;
                        if(j<S.size()&&S.get(j).equals("NAME"))
                        {
                            int k=j;
                            while(j<S.size()&&check(S.get(j)))j++;
                            String x="";
                            while(k<j)
                            {
                                if(!"".equals(x))
                                    x+=" ";
                                x+=S2.get(k);
                                AttachmentName.add(S2.get(k));
                                k++;
                            }
                            if(!x.equals(""))
                                AttachmentName.add(x);
                            break;
                        }
                    }
                }
            }
            //check x.(type) and All xyz pdfs
            if(AttachmentName.isEmpty())
                for(int i=0;i<S.size();i++)
                {
                    if(isType(S.get(i)))
                    {
                        int j=i-1;
                        if(j>=0&&S.get(j).endsWith("'S"))
                            continue;
                        if(j>=0&&S.get(j).equals("."))
                        {
                            AttachmentName.add(S2.get(j-1));
                        }
                        while(j>=0&&check(S.get(j)))j--;
                        j++;
                        String x="";
                        while(j<i)
                        {
                            if(!x.equals(""))
                                x+=" ";
                            x+=S2.get(j);
                            AttachmentName.add(S2.get(j));
                            j++;
                        }
                        if(!x.equals(""))
                            AttachmentName.add(x);
                        break;
                    }
                }
        }
        //check for CC
        for(int i=0;i<S.size();i++)
        {
            if(S.get(i).equals("CC")||S.get(i).equals("CC'D"))
            {
                int j=i+1;
                if(j==S.size())break;
                if(S.get(j).equals("TO"))j++;
                while(j<S.size()&&S.get(j).equals(":"))j++;
                String x="";
                while(j<S.size())
                {
                    if(",".equals(S.get(j)))
                    {
                        j++;
                        if(!"".equals(x))
                            CC.add(x);
                        x="";
                        continue;
                    }
                    else if("".equals(x)&&("ME".equals(S.get(j))||"I".equals(S.get(j))))
                    {
                        x=S2.get(j);
                    }
                    else if(".".equals(S.get(j)))
                    {
                        j++;
                        if(j<S.size())
                            x+="."+S2.get(j);
                    }
                    else if(check(S.get(j)))
                    {
                        if(!"".equals(x))
                            x+=" "+S2.get(j);
                        else
                            x=S2.get(j);
                    }
                    else break;
                    j++;
                }
                if(!"".equals(x))
                    CC.add(x);
                if(j<S.size()&&"AND".equals(S.get(j)))
                {
                    j++;
                    x=S2.get(j);
                    j++;
                    while(j<S.size())
                    {
                        if("".equals(x)&&("ME".equals(S.get(j))||"I".equals(S.get(j))))
                        {
                            x=S2.get(j);
                            break;
                        }
                        else if(".".equals(S.get(j)))
                        {
                            j++;
                            if(j<S.size())
                                x+="."+S2.get(j);
                        }
                        else if(check(S.get(j)))
                        {
                            x+=" "+S2.get(j);j++;
                        }
                        else
                            break;
                    }
                    if(!"".equals(x))
                        CC.add(x);
                }
                break;
            }
        }
        //check for subject
        for(int i=0;i<S.size();i++)
        {
            if(S.get(i).equals("SUBJECT")||S.get(i).equals("REGARDING")||S.get(i).equals("ABOUT")||S.get(i).equals("ON"))
            {
                int j=i+1;
                String x="", y="";
                if(S.get(j).equals("AS"))j++;
                else while(S.get(j).equals(":"))j++;
                while(j<S.size()&&!(S.get(j).equals(":")||!check(S.get(j))))
                {
                    y=x;
                    x+=" "+S2.get(j);
                    j++;
                }
                if(j==S.size()||!S.get(j-1).equals(":"))
                    Subject=x;
                else
                    Subject=y;
                break;
            }
        }

    }
    static String pr (ArrayList<String> c, String s)
    {
        if(c.isEmpty())
        {
            s+="Any";
        }
        else
        for(int i=0;i<c.size();i++)
        {
            String x=c.get(i);
            s+=x;
            if(i!=c.size()-1)
            {
                s+="/";
            }
        }
        return s;
    }
    public static String extractAttachmentSearchContextFromString(String s)
    {
        String ret="";
        String[] str = s.split("\\s+");
        init();
        solve(str);
        //System.out.print("From: ");
        ret+="From: ";
        ret=pr(From, ret);
        //System.out.print("\nTo: ");
        ret+="\nTo: ";
        ret=pr(To, ret);
        //System.out.println("\nToDate: "+ ToDate);
        ret+="\nToDate: "+ ToDate;
        //System.out.println("FromDate: "+ FromDate);
        ret+="\nFromDate: "+ FromDate;
        //System.out.println("HasAttachments: "+ HasAttachments);
        ret+="\nHasAttachments: "+ HasAttachments;
        //System.out.print("AttachmentType: ");
        ret+="\nAttachmentType: ";
        ret=pr(AttachmentType, ret);
        //System.out.println("\nAttachmentSize: "+ AttachmentSize);
        ret+="\nAttachmentSize: "+ AttachmentSize;
        //System.out.println("Subject: "+ Subject);
        ret+="\nSubject: "+ Subject;
        //System.out.print("CC: ");
        ret+="\nCC: ";
        ret=pr(CC, ret);
        //System.out.print("\nAttachmentName: ");
        ret+="\nAttachmentName: ";
        ret=pr(AttachmentName, ret);
        ret+="\n";
        return ret;
    }
    /*public static void main(String[] args) throws IOException {
        FileReader fin = null;
        FileWriter fout = null;

        try {
            fin = new FileReader("C:\\Users\\dell\\Desktop\\MailServer\\src\\mailserver\\CitrixTestData.txt");
            fout = new FileWriter("C:\\Users\\dell\\Desktop\\MailServer\\src\\mailserver\\output.txt");
            BufferedReader in = new BufferedReader(fin);
            BufferedWriter out = new BufferedWriter(fout);
            String s;
            init();
            while ((s = in.readLine())!=null) {
                out.write(s);
                out.newLine();
                System.out.println(s);
                String[] str = s.split("\\s+");
                solve(str);
                System.out.print("From: ");
                out.write("From: ");
                pr(From, out);
                System.out.print("\nTo: ");
                out.newLine();
                out.write("To: ");
                pr(To, out);
                System.out.println("\nFromDate: "+ FromDate);
                out.newLine();
                out.write("FromDate: "+ FromDate);
                out.newLine();
                System.out.println("ToDate: "+ ToDate);
                out.write("ToDate: "+ ToDate);
                out.newLine();
                System.out.println("HasAttachments: "+ HasAttachments);
                out.write("HasAttachments: "+ HasAttachments);
                out.newLine();
                System.out.print("AttachmentType: ");
                out.write("AttachmentType: ");
                pr(AttachmentType, out);
                System.out.println();
                out.newLine();
                System.out.println("AttachmentSize: "+ AttachmentSize);
                out.write("AttachmentSize: "+ AttachmentSize);
                out.newLine();
                System.out.print("AttachmentName: ");
                out.write("AttachmentName: ");
                pr(AttachmentName, out);
                System.out.println();
                out.newLine();
                System.out.println("Subject: "+ Subject);
                out.write("Subject: "+ Subject);
                out.newLine();
                System.out.print("CC: ");
                out.write("CC: ");
                pr(CC, out);
                System.out.println();
                out.newLine();
                System.out.println();
                out.newLine();
            }
            out.close();
            in.close();
        }finally {
            if (fin != null) {
                fin.close();
            }
            if (fout != null) {
                fout.close();
            }
        }

    }*/
}
