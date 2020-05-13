import java.util.*;

public class NewRegister2
{
    static ArrayList<BookInfo> tList = new ArrayList<BookInfo>();
    static ArrayList<BookInfo> fList = new ArrayList<BookInfo>();
    
    public void Register(String text, String type){
	String[] info = new String[100];
	info = text.split("\\s+");
	
	assert info.length == 5;
	String title = info[0]; String ISBN = info[1];
	String author = info[2];  String publisher = info[3];
	String pyear = info[4];

	if(type.equals("T")){
	    BookInfo tBook = new BookInfo(title,ISBN,author,publisher,pyear);
	    tList.add(tBook);
	}else if(type.equals("F")){
	    BookInfo fBook = new BookInfo(title,ISBN,author,publisher,pyear);
	    fList.add(fBook);
	}	
    }
    
    public String GetInfo(int book_num, String type, String info){ 
	if(type.equals("T")){
	    if(info.equals("title")){
		return (tList.get(book_num)).i_title;
	    }else if(info.equals("ISBN")){
		return (tList.get(book_num)).i_ISBN;
	    }else if(info.equals("author")){
		return (tList.get(book_num)).i_author; 
	    }else if(info.equals("publisher")){
		return (tList.get(book_num)).i_publisher;
	    }else if(info.equals("pyear")){
		return (tList.get(book_num)).i_pyear;
	    }
        }else if(type.equals("F")){
	    if(info.equals("title")){
                return (fList.get(book_num)).i_title;
            }else if(info.equals("ISBN")){
		return (fList.get(book_num)).i_ISBN;
            }else if(info.equals("author")){
                return (fList.get(book_num)).i_author;
            }else if(info.equals("publisher")){
                return (fList.get(book_num)).i_publisher;
            }else if(info.equals("pyear")){
		return (fList.get(book_num)).i_pyear;
            }
        }
	return "wrong";
    }
    
    
    public ArrayList<BookInfo> ListPass(){
	return  tList;
    } 

    public void Init(){
	tList.clear(); 
    }
}