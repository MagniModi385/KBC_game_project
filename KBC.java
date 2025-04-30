import java.sql.*;
import java.util.*;
import java.io.*;
class Bnode
{
  String Player;
  int age;
  int Easy_score;
  int Hard_score;
  Bnode left;
  Bnode right;
  public Bnode(String Player, int age, int Easy_score, int Hard_score) {
      this.Player = Player;
      this.age = age;
      this.Easy_score = Easy_score;
      this.Hard_score = Hard_score;
  }
}
class BST
{
  Bnode root;
  void insert(String Player,int age,int Easy_score , int Hard_score) {
    root = insertRec(root, Player,age,Easy_score,Hard_score);
}

 Bnode insertRec(Bnode Treenode,String Player,int age,int Easy_score , int Hard_score)
{
  if (Treenode == null) {
    Treenode = new Bnode(Player,age,Easy_score,Hard_score);
    return Treenode;
}
if(Player.compareTo(Treenode.Player)<0)
{
  Treenode.left= insertRec(Treenode.left, Player,age,Easy_score,Hard_score);
}
else if(Player.compareTo(Treenode.Player)>0)
{
  Treenode.right= insertRec(Treenode.right, Player,age,Easy_score,Hard_score);
}
return Treenode;
}
void  Display(String player)
{
  Bnode resultNode = search(root, player);
  if (resultNode != null) {
      System.out.println("Found: " + resultNode.Player);
  } else {
      System.out.println("Not found.");
  }
}

Bnode search(Bnode Treenode,String player)
{
if(Treenode==null)
{
  return null;
}
if(Treenode.Player.equals(player))
{
return Treenode;
}
if (player.compareTo(Treenode.Player) < 0) {
  return search(Treenode.left, player);
} else {
  return search(Treenode.right, player);
}
}
}
class KBC2_0 {
  public static void main(String[] args) throws Exception {
    Scanner sc=new Scanner(System.in);
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Game", "root", "");
        String CompTag=null;
      Statement st= con.createStatement();
    st.executeUpdate("Create table if not exists Player(PlayerTag varchar(50),Name varchar(50),Age int,Easy_score int ,Hard_score int)");
      System.out.println("WELCOME TO KBC");
      System.out.println("::::::::::::::::::::");
      System.out.println("Disclaimer:This is not an actual KBC game but rather a game based on that");
      System.out.println("THE RULES");
      System.out.println("::::::::::::::::::::");
      System.out.println("There are two modes in which this game can be played: Easy and Hard");
      System.out.println(":::::::::::::::::::");
      System.out.println("1)10 points are given for each correct answer and -5 points on a wrong answer in easy mode ");
      System.out.println(":::::::::::::::::::");
      System.out.println("2)20 points are given for each correct answer and -10 points on a wrong answer in hard mode ");
      System.out.println(":::::::::::::::::::");
      System.out.println("3)There is a 60 sec time limit for questions uptil 10 and after that there is all the time you need in EASY Mode");
      System.out.println(":::::::::::::::::::");
      System.out.println("4)Hard Mode has no time limit");
      System.out.println(":::::::::::::::::::");
      System.out.println("5)There are lifelines availaible in case you get stuck");
      System.out.println(":::::::::::::::::::");
      System.out.println("6)Please do not ask for MONEY as the creator of this game is jobless");
      System.out.println("::::::::::::::::::::");
      System.out.println("Press any key to continue");
      String del=sc.next();
      if(del.equals("LogRecords"))
      {
        Admin();
      }
      boolean b=true;
      int flag=0;
      while(b)
      {
        
        System.out.println("Press L to Login(Exsiting player)");
        System.out.println("Press R to Register(New player)");
           char ch=sc.next().charAt(0);
        if(ch=='l' || ch=='L')
        {
          System.out.println("Please Enter Player Tag");
          String pt=sc.next();
        String query="Select PlayerTag from Player";
        PreparedStatement pst=con.prepareStatement(query);
       ResultSet rs=pst.executeQuery();
       while(rs.next())
        {
        String tag=rs.getString("PlayerTag");
        if(tag.equals(pt))
        {
          flag=1;
          System.out.println("Player found!");
          b=false;
          CompTag=pt;
          break;
        }
        }
        if(flag==0){System.out.println("Player Not found");}
        }
        else if(ch=='r'||ch=='R')
        {
          //b=false;
          try
          {

          
            sc.nextLine();
            System.out.println("*********************");
            System.out.println("Please Enter player name");
            String P_name=sc.nextLine();
            System.out.println("*********************");
            System.out.println("Please Enter player's age");
            int P_age=sc.nextInt();
            System.out.println("*********************");
            int rd=(int)(Math.random()*1000)+1;
            String Player_Tag=P_name.replace(" ", "") +Integer.toString(rd);
            System.out.println("Your Player Tag is : " + Player_Tag);
            String sql1="Insert into Player(PlayerTag,Name,Age,Easy_score,Hard_score)values(?,?,?,?,?)";
            PreparedStatement pst=con.prepareStatement(sql1);
            pst.setString(1, Player_Tag);
            pst.setString(2, P_name);
            pst.setInt(3, P_age);
            pst.setInt(4, 0);
            pst.setInt(5, 0);
            int r=pst.executeUpdate();
            System.out.println((r>0)?"Player Registered":"Player not registered");
            CompTag=Player_Tag;
            break;}
            catch(Exception e) {System.out.println("error");}
      }
        }
        b=true;
        while(b)
        {
          System.out.println("Select Difficulty level");
          System.out.println("::::::::::::::::::::::::");
          System.out.println("Press E for Easy level ");
          System.out.println("Press H for Hard level");
          char ch=sc.next().charAt(0);
          if(ch=='E'|| ch=='e')
          {
            b=false;
            new questions(CompTag,con).easyQuestions();
          }
          else if(ch=='H'||ch=='h')
          {
                 b=false;
                 new hard(CompTag,con).hardquestions();
          }
        }
        }
        static void Admin() throws Exception
        {
         Scanner sc=new Scanner(System.in);
         Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Game", "root", "");
         boolean b=true;
         while(b)
         {
           System.out.println("Press 1 to Delete Log");
           System.out.println("Press 2 to display all logs");
           System.out.println("Press 3 to search a player by player Tag");
           System.out.println("Press 4 to exit");
           int ch=sc.nextInt();
             switch(ch)
             {
              case 1:{
                 System.out.println("Player Tag");
             CallableStatement cst=con.prepareCall("{call DELETION(?)}");
               cst.setString(1, sc.next());
               int r=cst.executeUpdate();
               System.out.println((r>0)?"Log Deleted":"Error");
               break;
              }
           case 2:
           {
            CallableStatement cst=con.prepareCall("{call Display()}");
               ResultSet rs=cst.executeQuery();
               ResultSetMetaData rsmd=rs.getMetaData();
               System.out.println(rsmd.getColumnName(1)+"::::" + rsmd.getColumnName(2)+"::::" + rsmd.getColumnName(3)+"::::" + rsmd.getColumnName(4)+"::::" + rsmd.getColumnName(5));
               System.out.println();
               while(rs.next())
               {
                System.out.println(rs.getString(1) + "||" + rs.getString(2) + "||" + rs.getInt(3) + "||"+rs.getInt(4) + "||" + rs.getInt(5));
               }
               System.out.println(":::::::::::::::::::::::::::::::::::");
               break;
           }
           case 3:
          {
                System.out.println("Enter PlayerTag"); 
              String searchname=sc.next();
                PreparedStatement pst=con.prepareStatement("Select * from Player");
                ResultSet rs=pst.executeQuery();
                while(rs.next())
                {
                  String player=rs.getString("PlayerTag");
                  String name=rs.getString("Name");
                  int age=rs.getInt("Age");
                  int Escore=rs.getInt("Easy_score");
                  int Hscore=rs.getInt("Hard_score");
                }
          }

           case 4:
           {
            b=false;
           }
            }
         }
        }
}

class node
{
  int qno;
  int score;
  boolean answer;
  node next;
  node(int qno,int score,boolean answer)
  {
    this.qno=qno;
     this.answer=answer;
     this.score=score;
  }
}
class valueSet
{
  node head;
  void addValue(int q,int s,boolean v)
  {
       node n=new node(q,s,v);
       if(head==null)
       {
        head=n;
       }
       else
       {
        node temp=head;
        while(temp.next!=null)
        {
              temp=temp.next;
        }
        temp.next=n;
       }
  }
  void emptylist()
  {
    head=null;
  }
  void display(String TempTag,int flag)throws Exception
  {
    File f;
    if(flag==1)
    {
      f=new File(TempTag+"_EASY_SCORE.txt");
    }
    else
    {
      f=new File(TempTag+"_HARD_SCORE.txt");
      
    }
    BufferedWriter br=new BufferedWriter(new FileWriter(f));
    node temp=head;
    int sum=0;
    
    while(temp!=null)
    {
       System.out.println("Question : " + temp.qno +")" + " Points: "+ temp.score + " Status: " + temp.answer);
       br.write("Question : " + temp.qno +")" + " Points: "+ temp.score + " Status: " + temp.answer); 
       br.newLine();
       br.flush();
       sum=sum+temp.score;
      temp=temp.next;
  }
  System.out.println("::::::::::::::::::::::::::::::");
  System.out.println("Total score : " + sum);
     Update(sum, TempTag,flag);
}
        void Update(int sum,String TempTag,int flag) throws Exception
        {
          Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Game", "root", "");
          if(flag==1)
          {
             try
             {

               CallableStatement cst=con.prepareCall("{call Update_Escore(?,?)}");   
               cst.setInt(1, sum);           
               cst.setString(2, TempTag);     
               int r=cst.executeUpdate();      
               System.out.println((r>0)?"Your Score has been Updated":"Error");
               System.out.println("Thank you for playing this game");
              }
              catch(Exception e)
              {
              System.out.println(e.getMessage());
              }
            
          }
          else
          {
            try
            {

              CallableStatement cst=con.prepareCall("{call Update_Hscore(?,?)}");   
              cst.setInt(1, sum);           
              cst.setString(2, TempTag);    
              int r=cst.executeUpdate();      
              System.out.println((r>0)?"Your Score has been Updated":"Error");
              
             }
             catch(Exception e)
             {
             System.out.println(e.getMessage());
             }
            }
          }
          }

class lifeLine
{
  Scanner sc=new Scanner(System.in);
  boolean flag1=true;
  boolean flag2=true;
  boolean flag3=true;
 // boolean flag4=true;
  char c[]={0,'b','a','c','b','d','c','b','c','d','a','c','d','c','a','c','d','b','a','c','a'};
  boolean chooseLifeline(int qno)
  {
   if(flag1)
   {
    System.out.println("Press F to flip the question");    
  }
   if(flag2)
   {
    System.out.println("Press E to ask the expert ");
   }
   if(flag3)
   {
    System.out.println("Press T for double dip");
   }
  //  if(flag4)
  //  {
  //   System.out.println("Press V for Audience vote ");
  //  }
   if(flag1==false && flag2==false && flag3==false)
   {
    System.out.println("You have no LifeLines Remaining");
    System.out.println("Enter your answer");
    char ans;
    while(true)
    {
      ans=sc.next().charAt(0);
      if((ans>='a' && ans<='d') || (ans>='A' && ans<='D'))
      {
        break;
      }
      System.out.println("Invalid Input");
    }
    if(ans==c[qno] || ans==c[qno]-32)
    {
     return true;
    }
    else
    {
      return false;
    }
   }
   char ch;
   while(true)
   {
      ch=sc.next().charAt(0); 
      if(ch=='f' || ch=='F'  || ch=='e' || ch=='E' || ch=='t' || ch=='T')
      {
        break;
      }
      System.out.println("Invalid Input");
   }
   if((ch=='F' || ch=='f')&& flag1)
   {
      boolean ans=flip();
      flag1=false;
      return ans;
   }
   else if((ch=='E' || ch=='e') && flag2)
   {
     boolean ans=askExpert(qno);
     flag2=false;
     return ans;
   }
   else if((ch=='T'||ch=='t')&& flag3)
   {
    boolean ans=doubleDip(qno);
    flag3=false;
    return ans;
   }
   else{return false;}
  
  }
  boolean flip()
  {
    int ch;
    boolean b=false;
    char ans;
    while(true){
        System.out.println("::::::::::::::::::::::::::::");
        System.out.println("Press 1 for Entertainment and movies");
        System.out.println("Press 2 for History and heritage");
        System.out.println("Press 3 for Nature and Wildlife");
        System.out.println("Press 4 for Science and Technology");
        System.out.println("Press 5 for Sports and games");
      ch=sc.nextInt();
        if(ch==1||ch==2||ch==3||ch==4||ch==5)
        {
          break;
        }
    }
    int r=(int)(Math.random()*2)+1;
    switch (ch) {
      case 1:
      {
          if(r==1)
          {
            System.out.println("Who directed the movie Gangs of Wassepur?");
            System.out.println("A)Karan Johar        B)Anurag Kashyap");
            System.out.println("C)Rohit Shetty       D)Abhishek Pathak");
            while(true)
            {
              ans=sc.next().charAt(0);
              if((ans>='a' && ans<='d') || (ans>='A' && ans<='D'))
              {
                break;
              }
              System.out.println("Invalid Input");
            }
            if(ans=='B'||ans=='b'){b=true;}
            else{b=false;}
          }
          else
          {
            System.out.println("Which Indian actor-turned politician won the 2017 Filmfare Lifetime award");
            System.out.println("A)Paresh Rawal        B)Vinod Khanna");
            System.out.println("C)Dharmendra          D)Shatrughan Sinha");
            while(true)
            {
              ans=sc.next().charAt(0);
      if((ans>='a' && ans<='d') || (ans>='A' && ans<='D'))
      {
        break;
      }
      System.out.println("Invalid Input");
            }
            if(ans=='D'||ans=='d'){b=true;}
            else{b=false;}
          }
          break;
      }
      case 2:
      {
        if(r==1)
          {
            System.out.println("Who was the mongol conqueror who reigned most of asia?");
            System.out.println("A)Gengis Khan        B)Napolean Bonaparte");
            System.out.println("C)Kublai Khan      D)Alexander the great");
            while(true)
            {
              ans=sc.next().charAt(0);
      if((ans>='a' && ans<='d') || (ans>='A' && ans<='D'))
      {
        break;
      }
      System.out.println("Invalid Input");
            }
            if(ans=='A'||ans=='a'){b=true;}
            else{b=false;}
          }
          else
          {
            System.out.println("What is the heigth of qutub minar?");
            System.out.println("A)75 m            B)8o m");
            System.out.println("C)72.5 m          D)83.4 m");
            while(true)
            {
              ans=sc.next().charAt(0);
      if((ans>='a' && ans<='d') || (ans>='A' && ans<='D'))
      {
        break;
      }
      System.out.println("Invalid Input");
            }
            if(ans=='C'||ans=='c'){b=true;}
            else{b=false;}
          }
          break;
      }
      case 3:
      { 
        if(r==1)
          {
            System.out.println("Which is the fastest animal in the world in terms of all domain?");
            System.out.println("A)Outback Turtle        B)Peregrine Falcon");
            System.out.println("C)Ostrich               D)Cheetah");
            while(true)
            {
              ans=sc.next().charAt(0);
              if((ans>='a' && ans<='d') || (ans>='A' && ans<='D'))
              {
                break;
              }
              System.out.println("Invalid Input");
            }
            if(ans=='B'||ans=='b'){b=true;}
            else{b=false;}
          }
          else
          {
            System.out.println("Which is the tallest tree in the world");
            System.out.println("A)Coast Redwood        B)Eastern Sandalwood");
            System.out.println("C)Himalayan Cypress       D)Norther Oakwood");
            while(true)
            {
              ans=sc.next().charAt(0);
              if((ans>='a' && ans<='d') || (ans>='A' && ans<='D'))
              {
                break;
              }
              System.out.println("Invalid Input");
            }
            if(ans=='A'||ans=='a'){b=true;}
            else{b=false;}
          }
          break;
      }
       case 4:
       {
        if(r==1)
        {
          System.out.println("What is the name of first Indian Missile ");
          System.out.println("A)Asurastra        B)Prithvi");
          System.out.println("C)Dhanush             D)Agni");
          while(true)
          {
            ans=sc.next().charAt(0);
      if((ans>='a' && ans<='d') || (ans>='A' && ans<='D'))
      {
        break;
      }
      System.out.println("Invalid Input");
          }
          if(ans=='B'||ans=='b'){b=true;}
          else{b=false;}
        }
        else
        {
          System.out.println("What is the scientific name for humans");
          System.out.println("A)Homo Habilis        B)Homo Sapiens Linnaeus");
          System.out.println("C)Homo Erectus       D)Homo Sapiens Sapiens");
          while(true)
            {
              ans=sc.next().charAt(0);
      if((ans>='a' && ans<='d') || (ans>='A' && ans<='D'))
      {
        break;
      }
      System.out.println("Invalid Input");
            }
          if(ans=='d'||ans=='D'){b=true;}
          else{b=false;}
        }
        break;
       }  
       case 5:
       {
        if(r==1)
        {
          System.out.println("What position is beside the wicket keeper in cricket?");
          System.out.println("A)Slip               B)Mid off");
          System.out.println("C)Square Leg         D)Cover");
          while(true)
          {
            ans=sc.next().charAt(0);
      if((ans>='a' && ans<='d') || (ans>='A' && ans<='D'))
      {
        break;
      }
      System.out.println("Invalid Input");
          }
          if(ans=='A'||ans=='a'){b=true;}
          else{b=false;}
        }
        else
        {
          System.out.println("Who is the highest goal scorer of football?");
          System.out.println("A)Zlatan Imbhrimovic        B)Cristiano Ronaldo");
          System.out.println("C)Romario             D)Lionel Messi");
          while(true)
            {
              ans=sc.next().charAt(0);
              if((ans>='a' && ans<='d') || (ans>='A' && ans<='D'))
              {
                break;
              }
              System.out.println("Invalid Input");
            }
          if(ans=='b'||ans=='B'){b=true;}
          else{b=false;}
        }
        break;
       }
    }
    return b;
  }
 boolean askExpert(int qno)
 { 
  char ans;
  System.out.println("Please see that the expert answer being correct ");
  System.out.println("has a probability of 8/10 ");
  System.out.println(":::::::::::::");
  int r=(int)(Math.random()*10)+1;
if(r<2 || qno==1)
{
  System.out.println("The correct answer might be : " + c[qno]);
}
else
{
  System.out.println("The correct answer might be : " + c[qno-1]);
}
  System.out.println("Enter your choice");
  while(true)
  {
    ans=sc.next().charAt(0);
     if(ans=='a' || ans=='A' || ans=='b' || ans=='B' ||ans=='c' || ans=='C' || ans=='d' || ans=='D' )
     {
      break;
     } 
     System.out.println("Invalid Input");
  }
  if(ans==c[qno] || ans==c[qno]-32)
  {
    return true;
  }
  else
  {
    return false;
  }
 }
 boolean doubleDip(int qno)
 {
  boolean ans=false;
     System.out.println("Enter Your choice");
     int i=0;
     char ch;
     while(i!=2)
     {
         while(true)
         {
         ch=sc.next().charAt(0);
        if((ch>='a' && ch<='d') || (ch>='A' && ch<='D'))
        {
          break;
         }
         System.out.println("Invalid Input");
        }
      if(ch==c[qno] || ch==c[qno]-32)
      {
         ans=true;
          break;
      }
     if(i==0)
      {
        System.out.println("Wrong Answer!");
      }
      i++;
     }
     return ans;
 }
}
class questions extends Thread
{
  Scanner sc=new Scanner(System.in);
  String TempTag;
  Connection con;
  questions()
  {}
Thread t[];
  questions(String TempTag,Connection con)
  {
    this.con=con;
   this.TempTag=TempTag;
   t=new Thread[11];
   for(int i=1;i<=10;i++)
   {
    t[i]=new Thread(new questions());
   }
  }
  public valueSet vs=new valueSet();  
  lifeLine l1=new lifeLine();
   static boolean b=true;
  void easyQuestions()throws Exception
  {
      for(int i=1;i<=20;i++)
      {
            PreparedStatement pst=con.prepareStatement("Select * from easy_questions where Q_no=?");
            pst.setInt(1, i);
            ResultSet rs=pst.executeQuery();
            while(rs.next() && b)
            {
              String ans=rs.getString("Correct_Option");
              System.out.println("Question " + i);
            System.out.println("::::::::::::::::::::");
            System.out.println(rs.getString("Question"));
            System.out.println("A)"+rs.getString("Option_A") + "          " + "B)"+rs.getString("Option_B"));
            System.out.println("C)"+rs.getString("Option_C") + "          " + "D)"+rs.getString("Option_D"));
            System.out.println("::::::::::::::::::::");
            System.out.println("Press L for Lifeline");
            if(i<=10)
            {
              t[i].start();
            }
            String attempt;
            while(true)
            {
              attempt=sc.next();
              if(attempt.equalsIgnoreCase("exit"))
              {
                b=false;
                break;
              }
              if(attempt.length()==1 && (attempt.equalsIgnoreCase("a")|| attempt.equalsIgnoreCase("b")|| attempt.equalsIgnoreCase("c")|| attempt.equalsIgnoreCase("d")||attempt.equalsIgnoreCase("l")))  
              {
                break;
              }
              System.out.println("Invalid Input,Please Put correct Option");
            }
            if(b==false)
            {
              continue;
            }
            if(attempt.equalsIgnoreCase(ans))
            {
              System.out.println("Correct Answer!");
              System.out.println("::::::::::::::::::::");
              vs.addValue(i,10,true);
            }
            else if(attempt.equalsIgnoreCase("l"))
            {
              if(l1.chooseLifeline(i))
              {
                System.out.println("Correct Answer!");
                System.out.println("::::::::::::::::::::");
                vs.addValue(i,10,true);
              }
              else
              {
                System.out.println("Wrong Answer!");
                System.out.println("The correct answer is " + ans);
                System.out.println("::::::::::::::::::::");
                vs.addValue(i,-5,false);
              }
            }
            else
            {
              System.out.println("Wrong Answer!");
              System.out.println("The correct answer is "+ ans);
              System.out.println("::::::::::::::::::::");
              vs.addValue(i,-5,false);
            }
            if(i<=10)
            {
              try
              {
               t[i].interrupt();
              }
              catch(Exception e){}
            }
          } 
      }
      vs.display(TempTag, 1);
      vs.emptylist();
      new select().selectagain(TempTag,con);
  }
  public void run()
  {
    try
    {
      sleep(30000);
      System.out.println("30 seconds remaining");  
        sleep(15000);
       for(int i=15 ;i>=0; i--)
       {
       System.out.print(i+",");
       Thread.sleep(1000);
       }
       System.out.println();
      System.out.println("You lost the game!");
      vs.display(TempTag, 1);
     b=false;
     System.out.println("Press A to continue");
      return;
  //System.exit(0);
    }
    catch(Exception e){}
  }
}
   class hard
   {
    Scanner sc=new Scanner(System.in);
    String TempTag;
    valueSet vs=new valueSet();
   hardlines h1=new hardlines();
   Connection con;
    hard(String TempTag,Connection con)
    {
      this.TempTag=TempTag;
      this.con=con;
    }
    void hardquestions() throws Exception
    {
      System.out.println("Good luck!");
      System.out.println("###############################");
      int r;
           for(int i=1;i<=10;i++)
           {
           
            PreparedStatement pst;
            ResultSet rs;
           r=(int)(Math.random()*3)+1;
          if(r==1)
          {
           pst=con.prepareStatement("Select * from Hard_Set1 where Q_no=?");
            pst.setInt(1, i);
          rs=pst.executeQuery();
          }
          else if(r==2)
          {
             pst=con.prepareStatement("Select * from Hard_Set2 where Q_no=?");
            pst.setInt(1, i);
             rs=pst.executeQuery();
          }
          else
          {
            pst=con.prepareStatement("Select * from Hard_Set3 where Q_no=?");
            pst.setInt(1, i);
          rs=pst.executeQuery();
          }
          while(rs.next())
          {
            String ans=rs.getString("Correct_Option");
              System.out.println("Question " + i);
            System.out.println("::::::::::::::::::::");
            if(r==1)
            {
              System.out.println(rs.getString("Question_S1"));
            }
            else if(r==2)
            {
              System.out.println(rs.getString("Question_S2"));
            }
            else
            {
              System.out.println(rs.getString("Question_S3"));
            }
            System.out.println("A)"+rs.getString("Option_A") + "          " + "B)"+rs.getString("Option_B"));
            System.out.println("C)"+rs.getString("Option_C") + "          " + "D)"+rs.getString("Option_D"));
            System.out.println("::::::::::::::::::::");
            System.out.println("Press L for Lifeline");
            String attempt;
            while(true)
            {
              attempt=sc.next();
              if(attempt.length()==1 && (attempt.equalsIgnoreCase("a")|| attempt.equalsIgnoreCase("b")|| attempt.equalsIgnoreCase("c")|| attempt.equalsIgnoreCase("d")||attempt.equalsIgnoreCase("l")))  
              {
                break;
              }
              System.out.println("Invalid Input,Please Put correct Option");
            }
            if(attempt.equalsIgnoreCase(ans))
            {
              System.out.println("Correct Answer!");
              System.out.println("::::::::::::::::::::");
              vs.addValue(i,20,true);
            }
            else if(attempt.equalsIgnoreCase("l"))
            {
              if(h1.choose(i,r,con))
              {
                System.out.println("Correct Answer!");
                System.out.println("::::::::::::::::::::");
                vs.addValue(i,20,true);
              }
              else
              {
                System.out.println("Wrong Answer!");
                System.out.println("The correct answer is " + ans);
                System.out.println("::::::::::::::::::::");
                vs.addValue(i,-10,false);
              }
            }
            else
            {
              System.out.println("Wrong Answer!");
              System.out.println("The correct answer is "+ ans);
              System.out.println("::::::::::::::::::::");
              vs.addValue(i,-10,false);
            }
          }
           }
           vs.display(TempTag, 2);
           vs.emptylist();
           new select().selectagain(TempTag,con);
    }
   }
   class hardlines
   {
    Scanner sc=new Scanner(System.in);
    boolean flag1=true;
   boolean flag2=true;
    // boolean flag3=true;
    boolean choose(int qno, int set,Connection con) throws Exception
    {
       if(flag1)
       {
        System.out.println("Press E to ask the expert");
       }
       if(flag2)
       {
        System.out.println("Press T for double dip");
       }
       if(flag1==false && flag2==false)
       {
        System.out.println("You have no LifeLines Remaining");
        System.out.println("Enter your answer");
      String attempt;
          while(true)
          {
            attempt=sc.next();
            if(attempt.length()==1 && (attempt.equalsIgnoreCase("a")|| attempt.equalsIgnoreCase("b")|| attempt.equalsIgnoreCase("c")|| attempt.equalsIgnoreCase("d")))  
            {
              break;
            }
            System.out.println("Invalid Input,Please Put correct Option");
          }
          PreparedStatement pst=con.prepareStatement("Select * from getAns where Q_no=?");
          pst.setInt(1, qno);
          ResultSet rs=pst.executeQuery();
          while(rs.next())
          {
            if(set==1)
            {
                     if(attempt.equalsIgnoreCase(rs.getString("Ans_S1")))
                     {
                      return true;
                     }else{return false;}
            }
           else if(set==2)
            {
              if(attempt.equalsIgnoreCase(rs.getString("Ans_S2")))
              {
               return true;
              }else{return false;}
            }
           else if(set==3)
           {
            if(attempt.equalsIgnoreCase(rs.getString("Ans_S3")))
              {
               return true;
              }else{return false;}
           }
          } 
       }
       String at;
        while(true)
       {
         at=sc.next();
         if(at.length()==1 && (at.equalsIgnoreCase("E")|| at.equalsIgnoreCase("T")))  
         {
           break;
         }
         System.out.println("Invalid Input,Please Put correct Option");
       }
       if(at.equalsIgnoreCase("e") && flag1)
       {
        flag1=false;
        return callExpert(qno, set,con);
       }
       if(at.equalsIgnoreCase("t")&& flag2)
       {
          flag2=false;
          return hdg(qno, set, con);
       }
       else
       {
        return false;
       }
      
    }
    boolean callExpert(int qno,int set,Connection con) throws Exception
    {
      PreparedStatement pst=con.prepareStatement("Select * from getAns where Q_no=?");
      pst.setInt(1, qno);
      ResultSet rs=pst.executeQuery();
      System.out.println("According to the expert");
      while(rs.next())
      {
      if(set==1)
      {
          System.out.println("Your Answer might be: " + rs.getString("Ans_S1"));
          System.out.println("Enter your answer");
          String attempt;
          while(true)
          {
            attempt=sc.next();
            if(attempt.length()==1 && (attempt.equalsIgnoreCase("a")|| attempt.equalsIgnoreCase("b")|| attempt.equalsIgnoreCase("c")|| attempt.equalsIgnoreCase("d")))  
            {
              break;
            }
            System.out.println("Invalid Input,Please Put correct Option");
          }
          if(attempt.equalsIgnoreCase(rs.getString("Ans_S1")))
          {
            return true;
          }else{return false;}
        }
        if(set==2)
        {
            System.out.println("Your Answer might be: " + rs.getString("Ans_S2"));
            System.out.println("Enter your answer");
            String attempt;
            while(true)
            {
              attempt=sc.next();
              if(attempt.length()==1 && (attempt.equalsIgnoreCase("a")|| attempt.equalsIgnoreCase("b")|| attempt.equalsIgnoreCase("c")|| attempt.equalsIgnoreCase("d")))  
              {
                break;
              }
              System.out.println("Invalid Input,Please Put correct Option");
            }
            if(attempt.equalsIgnoreCase(rs.getString("Ans_S2")))
            {
              return true;
            }else{return false;}
          }
          if(set==3)
          {
              System.out.println("Your Answer might be: " + rs.getString("Ans_S3"));
              System.out.println("Enter your answer");
              String attempt;
              while(true)
              {
                attempt=sc.next();
                if(attempt.length()==1 && (attempt.equalsIgnoreCase("a")|| attempt.equalsIgnoreCase("b")|| attempt.equalsIgnoreCase("c")|| attempt.equalsIgnoreCase("d")))  
                {
                  break;
                }
                System.out.println("Invalid Input,Please Put correct Option");
              }
              if(attempt.equalsIgnoreCase(rs.getString("Ans_S3")))
              {
                return true;
              }else{return false;}
            }

      }
        return false;
    }
    boolean hdg(int qno,int set,Connection con ) throws Exception
    {
boolean Status=false;
      PreparedStatement pst=con.prepareStatement("Select * from getAns where Q_no=?");
      pst.setInt(1, qno);
      ResultSet rs=pst.executeQuery();
      String ans="null";
      System.out.println("Enter your answer");
      while(rs.next())
      {
        if(set==1)
        {
          ans=rs.getString("Ans_S1");
        }
        if(set==2)
        {
          ans=rs.getString("Ans_S2");
        }
        if(set==3)
        {
          ans=rs.getString("Ans_S3");
        }
      }
      int i=0;
      String attempt;
    while(i!=2)
    {
      while(true)
      {
        attempt=sc.next();
        if(attempt.length()==1 && (attempt.equalsIgnoreCase("a")|| attempt.equalsIgnoreCase("b")|| attempt.equalsIgnoreCase("c")|| attempt.equalsIgnoreCase("d")))  
        {
          break;
        }
        System.out.println("Invalid Input");
      }   
      if(attempt.equalsIgnoreCase(ans))
      {
        Status=true;
        break;
      }
    if(i==0)
    {
      System.out.println("Wrong Answer!");
    }
    }
      return Status;
    }
   }     
       class select
       {
        Scanner sc=new Scanner(System.in);
        void selectagain(String temptag,Connection con) throws Exception
        {
          
            System.out.println("Do you Wish to Play Again?");
            System.out.println("::::::::::::::::::::::::::::");
            System.out.println("Press E to Play Easy ");
            System.out.println("Press H to Play Hard");
            System.out.println("Or Press Any Key to exit");
            char ch=sc.next().charAt(0);
            if(ch=='E'|| ch=='e')
          {
           questions q1= new questions(temptag,con);
            q1.b=true;
            q1.easyQuestions();
          }
          else if(ch=='H'||ch=='h')
          {
               new hard(temptag, con).hardquestions();
          }
          else
          {
            System.exit(0);
          }
          
        }
      }
    
