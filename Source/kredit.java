
import java.util.*;
import java.text.*;

//--------------------- classes for calculate -------------------------------

class kredit{
 public double[][] mas_pr_an = new double[1200][6]; //массив результата аннуитета 
 public double[][] mas_pr_dif = new double[1200][6]; //массив результата дифференц
 private boolean komis1 = false;
 private double Komis1 = 0;

 private boolean komis2 = false;
 private double Komis2 = 0;

 private boolean komis3 = false;
 private double Komis3 = 0;

 private double summa = 0, summd = 0;

//calculation of the table for each month for annuitet
  void calc_tabl_an(double sum,double pr,int per, int fm){
	double prr, an;
     an = calc_an(sum,pr,per,fm);
	summa = sum;
	for(int i=0; i<per; i++){
      prr = sum*(pr/1200);
      mas_pr_an[i][0] = i;
      mas_pr_an[i][1] = sum;
      mas_pr_an[i][2] = prr;
      mas_pr_an[i][3] = sum+prr;
      mas_pr_an[i][4] = an;
      sum = (sum+prr)-an;
      mas_pr_an[i][5] =sum;
    }
  }

//calculation of the annuitant
 double calc_an(double sum,double pr,int per,int f){
    double tmp1 = 0.0, tmp2 = 0.0;

     tmp1 = 1+(pr/1200.0);
  switch (f){
	case 0:
    		tmp2 = Math.pow(tmp1,-(double)per);
		break;
	case 1:
    		tmp2 = Math.pow(tmp1,1.0-(double)per);
		break;
	case 2:
    		tmp2 = Math.pow(tmp1,2.0-(double)per);  		
  }
	return  (sum*(pr/1200))/(1-tmp2);
}

//calculation of the table for each month for differ
 void calc_tabl_dif(double sum,double pr,int per,int day, int f_mes,int f_god){
   	double prr,plat;
     int mes_day,god_day;
	summd = sum;
     plat = sum/(double)per;

	god_day = 365;
	if (f_god%4 == 0) god_day = 363;
     mes_day = kol_d(f_mes++,f_god);
	mes_day = mes_day-day;
	if (f_mes>12){
		f_mes = 1;
		f_god++;
	}
   for(int i=0; i<per; i++){
      mas_pr_dif[i][0] = i;
      mas_pr_dif[i][1] = sum;
      prr = (sum*(pr/100)*mes_day)/god_day;
      mas_pr_dif[i][2] = prr;
      mas_pr_dif[i][3] = plat;
      mas_pr_dif[i][4] = prr+plat;
      sum = sum-plat;
      mas_pr_dif[i][5] =sum;

	 god_day = 365;
		if (f_god%4 == 0) god_day = 363;
     	mes_day = kol_d(f_mes++,f_god);
		if (f_mes>12){
			f_mes = 1;
			f_god++;
		}
   }
  }
 int kol_d(int m,int g){
 switch (m){
	case 2:
		if (g%4 == 0) return 29;
		else	return 28;
	case 4: 
		return 30;
	case 6: 
		return 30;
	case 9: 
		return 30;
	case 11: 
		return 30;
	}
   defoult: 
		return 31;
 }
  double calc_sum_an(double plat,double pr,int per){
	double tmp1, tmp2;
	tmp1 = 1+(pr/1200.0);
	tmp2 = Math.pow(tmp1,-(double)per);
	return ((1-tmp2)*plat)/(pr/1200);
  }
  double calc_sum_dif(double plat,double pr,int per){
	return plat/((1/(double)per)+(pr*0.0008219)); 
  }
//------------ комиссии -------------------------------
 void setKomis1(boolean s){
 	komis1 = s;
 }

 boolean getKomis1(){
 	return komis1;
 }

 void setKomisVal1(double k){
 	Komis1 = k;
 }

 double getKomisVal1(){
 	return Komis1;
 }
//------------------------------
 void setKomis2(boolean s){
 	komis2 = s;
 }

 boolean getKomis2(){
 	return komis2;
 }

 void setKomisVal2(double k){
 	Komis2 = k;
 }

 double getKomisVal2(){
 	return Komis2;
 }
//-----------------------------
 void setKomis3(boolean s){
 	komis3 = s;
 }

 boolean getKomis3(){
 	return komis3;
 }

 void setKomisVal3(double k){
 	Komis3 = k;
 }

 double getKomisVal3(){
 	return Komis3;
 }
//-----------------------------

 double getSummA(){
 	return summa;
 }
 double getSummD(){
 	return summd;
 }


}
