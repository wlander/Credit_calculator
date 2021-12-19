import java.text.*;
import java.util.*;
import javax.swing.table.*;


class html_rep {

	String create_table(TableModel tbl_mod, String head, String[] col_head){
		int i,j;
		String str_table,str_row,str_val;

		int row = tbl_mod.getRowCount();
		int column = tbl_mod.getColumnCount();
		str_table = "<FONT SIZE=2>\n<TABLE ALIGN=CENTER WIDTH=40% BORDER=1>\n";
		str_table = str_table+"<CAPTION>"+head+"</CAPTION>\n<TR>\n"; 
		for (j=0;j<column;j++) str_table = str_table+"<TH>"+col_head[j]+"</TH>";
		str_table = str_table+"</TR>\n";
		for (i=0;i<row;i++){
			str_table = str_table+"<TR>\n";

			str_row = " ";
			for (j=0;j<column;j++){
				str_val = (tbl_mod.getValueAt(i,j)).toString();
				str_row = str_row+"<TD>"+str_val+"</TD>";
			}
		str_table = str_table+str_row+"\n</TR>\n";
		}
		str_table = str_table+"<TABLE>\n</FONT>\n";
		return str_table;
	}//end create_table
}