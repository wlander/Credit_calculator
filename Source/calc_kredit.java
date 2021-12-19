
//------------------- imported library
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.text.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.filechooser.*;

//----------------- main class for GUI ----------------------------------------------
public class calc_kredit extends JFrame {
//--------------------------------------------------------------------------------
 Color cl2 = new Color(197,255,255);
 Color cl = new Color(225,255,255);
 JComboBox cbx_tip;
 private String[] elem_tip = {"расч. ежемес. платежей", "расчет по сумме платежа", "расч. макс. суммы кред."};
 JLabel lbl1;
 JLabel lbl2;
 JLabel lbl3;

 JComboBox cbx_val;
 private String[] elem_val = {"рубли", "долл.США", "евро"};
 JTextField txt_kurs;
 JButton btn_gkurs;
 JLabel lbl_kurs;
 JTextField txt_sum;
 JCheckBox ckb_let;
 JTextField txt_srok;
 JTextField txt_pr;
 JButton btn_run;
 JCheckBox ckb_dat;
 
 drawPanel dpanel;
 JComboBox cbx_graph;
 private String[] elem_gr = {"мес. платеж", "проценты", "опл. кредита"};
 Color cl20 = new Color(204,255,204);
 Color cl21 = new Color(224,255,224);
 Color cl30 = new Color(153,204,255);
 Color cl40 = new Color(173,214,173);


 Dimension sizeP = new Dimension(470, 200);
 double draw_mas1[] = new double[1000];
 double draw_mas2[] = new double[1000];

 JTable tbl_rez;
 String column_rez[] = {"тип кред.","cумма", "платеж", "стоим-ть","общ. выплата"};
 String rez1[] = {"аннуитет"," ", " ", " "," "};
 String rez2[] = {"диференц"," ", " ", " "," "};
 String rez3[] = {"разница"," ", " ", " "," "};
 DefaultTableModel dtm_rez;

 JTable tbl_an;
 String column_an[] = {"месяц", "ост. начало", "процент","ост.+проц.","аннуитет","ост. на конец"};
 DefaultTableModel dtm_an;
 JTable tbl_dif;
 String[] column_dif = {"месяц", "ост. начало", "процент","опл.кредита","платеж","ост. на конец"};
 DefaultTableModel dtm_dif;

 Calendar my_cal;
 SpinnerModel spmd_date;

 JDialog dialog_info;

 String str_lihva1 = "И будешь давать взаймы многим народам, а сам не будешь брать взаймы; и господствовать будешь над многими народами, а они над тобою не будут господствовать.\n    Библия, Второзаконие 28 стих. \n\n";

 String str_lihva2 = "Не отдавай в рост брату твоему ни серебра, ни хлеба, ни чего-либо другого, что можно отдавать в рост; 20 иноземцу отдавай в рост, а брату твоему не отдавай в рост, чтобы Господь Бог твой благословил тебя во всем, что делается руками твоими, на земле, в которую ты идешь, чтобы овладеть ею.\n    Библия, Второзаконие 23 стих. \n\n";

 String str_lihva3 = "Те, которые пожирают рост, восстанут только такими же, как восстанет тот, кого повергает сатана своим прикосновением. Это — за то, что они говорили: “Ведь торговля — то же, что рост.” (Саблуков: «лихва — то же, что прибыль в торговле»). А Аллах разрешил торговлю и запретил рост. К кому приходит увещание от его Господа и он удержится, тому прощено, что предшествовало: дело его принадлежит Аллаху; а кто повторит, те — обитатели огня, они в нём вечно пребывают! \n    Коран, Сура 2 \n\n";

 String str_lihva4 = "И разукрасил шайтан им их деяния и отвратил их от пути, а были они зрячими. (...) 39. И всех Мы взяли за их грех: на некоторых из них Мы посылаем вихрь, некоторых постиг вопль, некоторых из них Мы заставили поглотить землю, некоторых Мы потопили. Аллах не был таким, чтобы их тиранить, но они сами себя тиранили!\n    Коран, Сура 29 \n\n";

 JDialog dialog_options;
 JComboBox cbx_formula;
 JTextField txt_pr2;
 JComboBox cbx_otch;
 JCheckBox ckb_komis;
 JCheckBox ckb_komis2;
 JCheckBox ckb_komis3;
 JTextField txt_komis;
 JTextField txt_komis2;
 JTextField txt_komis3;
 JButton btn_ok;
 private String[] elem_formula = {"p/[1-(1+p)^-n]", "p/[1-(1+p)^(1-n)]", "p/[1-(1+p)^(2-n)]"};
 private String[] tip_otcheta = {"аннуитет+диффер-ый", "аннуитет", "диффер-ый"};

 JDialog dialog_dop;
 JTable tbl_dop;
 DefaultTableModel dtm_dop;
 JButton btn_dop_ok;
 String str_d1[] = {"процент", "сумма", "тип","описание"};
 
 JButton btn_add;
 JDialog dialog_summ;
 JTable tbl_summ;
 DefaultTableModel dtm_summ;
 JButton btn_save_summ;
 JButton btn_summ_ok;
 String str_summh[] = {"№", "тип", "срок","проц. ст.","валюта","сумма","ежемес.платеж","стоимость","выплата"};

 JDialog dialog_graph;
 JButton btn_graph_ok;

 kredit kred;
 int ff = 0;
 int count_summ = 1;
 double pr_doh = 50.0;
//-------------------------------------------------------
public calc_kredit(){
 super("Lihva2.7.5_08");
 setDefaultCloseOperation( EXIT_ON_CLOSE );

 kred = new kredit();

 SpringLayout sl = new SpringLayout();
 JPanel pnl1 = new JPanel(sl);
 pnl1.setBackground(cl40);
 pnl1.setBorder(new LineBorder(cl30, 2));

 JToolBar my_toolbar = new JToolBar();
 my_toolbar.setPreferredSize(new Dimension(500, 30));
 my_toolbar.add(new CloseAction());
 my_toolbar.add(new JLabel("   "));
 my_toolbar.add(new ReportAction());
 my_toolbar.add(new SummAction());
 my_toolbar.add(new GraphAction());
 my_toolbar.add(new DopAction());
 my_toolbar.add(new OptionsAction());
 my_toolbar.add(new InfoAction());




 my_toolbar.add(new JLabel(" "));
 my_toolbar.add(new JLabel(new ImageIcon("images\\header4.png")));
 pnl1.add(my_toolbar);

 JLabel lbl_tip = new JLabel("тип расчета");
 sl.putConstraint(SpringLayout.WEST, lbl_tip, 30, SpringLayout.WEST, pnl1);
 sl.putConstraint(SpringLayout.NORTH, lbl_tip, 5, SpringLayout.SOUTH, my_toolbar);
 pnl1.add(lbl_tip);

 cbx_tip = new JComboBox(elem_tip);
 cbx_tip.setToolTipText("гыгы");
 sl.putConstraint(SpringLayout.WEST, cbx_tip, 70, SpringLayout.EAST, lbl_tip);
 sl.putConstraint(SpringLayout.NORTH, cbx_tip, 5, SpringLayout.SOUTH, my_toolbar);
 cbx_tip.addActionListener(new ActionSel());
 pnl1.add(cbx_tip);

 JLabel lbl_val = new JLabel("валюта");
 sl.putConstraint(SpringLayout.WEST, lbl_val, 30, SpringLayout.WEST, pnl1);
 sl.putConstraint(SpringLayout.NORTH, lbl_val, 4, SpringLayout.SOUTH, cbx_tip);
 pnl1.add(lbl_val);

 cbx_val = new JComboBox(elem_val);
 sl.putConstraint(SpringLayout.WEST, cbx_val, 95, SpringLayout.EAST, lbl_val);
 sl.putConstraint(SpringLayout.NORTH, cbx_val, 4, SpringLayout.SOUTH, cbx_tip);
 cbx_val.addActionListener(new ActionVal());
 pnl1.add(cbx_val);

 lbl_kurs = new JLabel("курс");
 lbl_kurs.setVisible(false);
 sl.putConstraint(SpringLayout.WEST, lbl_kurs, 5, SpringLayout.EAST, cbx_val);
 sl.putConstraint(SpringLayout.NORTH, lbl_kurs, 4, SpringLayout.SOUTH, cbx_tip);
 pnl1.add(lbl_kurs);

 txt_kurs = new JTextField(" ",4);
 txt_kurs.setVisible(false);
 sl.putConstraint(SpringLayout.WEST, txt_kurs, 3, SpringLayout.EAST, lbl_kurs);
 sl.putConstraint(SpringLayout.NORTH, txt_kurs, 4, SpringLayout.SOUTH, cbx_tip);
 pnl1.add(txt_kurs);

 btn_gkurs = new JButton("g");
 btn_gkurs.setVisible(false);
 btn_gkurs.setPreferredSize(new Dimension(20, 20));
 btn_gkurs.setToolTipText("гыгы");
 sl.putConstraint(SpringLayout.WEST, btn_gkurs, 3, SpringLayout.EAST, txt_kurs);
 sl.putConstraint(SpringLayout.NORTH, btn_gkurs, 4, SpringLayout.SOUTH, cbx_tip);
 //btn_gkurs.addActionListener(new ActionRun());
 pnl1.add(btn_gkurs);

 lbl1 = new JLabel("сумма кредита  ");
 sl.putConstraint(SpringLayout.WEST, lbl1, 30, SpringLayout.WEST, pnl1);
 sl.putConstraint(SpringLayout.NORTH, lbl1, 4, SpringLayout.SOUTH, cbx_val);
 pnl1.add(lbl1);

 txt_sum = new JTextField("1500000.0",10);
 sl.putConstraint(SpringLayout.WEST, txt_sum, 45, SpringLayout.EAST, lbl1);
 sl.putConstraint(SpringLayout.NORTH, txt_sum, 4, SpringLayout.SOUTH, cbx_val);
 pnl1.add(txt_sum);

 lbl2 = new JLabel("срок кредита");
 sl.putConstraint(SpringLayout.WEST, lbl2, 30, SpringLayout.WEST, pnl1);
 sl.putConstraint(SpringLayout.NORTH, lbl2, 4, SpringLayout.SOUTH, txt_sum);
 pnl1.add(lbl2);

 txt_srok = new JTextField("10",10);  
 sl.putConstraint(SpringLayout.WEST, txt_srok, 60, SpringLayout.EAST, lbl2);
 sl.putConstraint(SpringLayout.NORTH, txt_srok, 4, SpringLayout.SOUTH, txt_sum);
 pnl1.add(txt_srok);

 ckb_let = new JCheckBox("месяцев");
 ckb_let.setBackground(cl40);
 sl.putConstraint(SpringLayout.WEST, ckb_let, 3, SpringLayout.EAST, txt_srok);
 sl.putConstraint(SpringLayout.NORTH, ckb_let, 4, SpringLayout.SOUTH, txt_sum);
 pnl1.add(ckb_let);
 
 JLabel lbl3 = new JLabel("проц. ставка (год.)");
 sl.putConstraint(SpringLayout.WEST, lbl3, 30, SpringLayout.WEST, pnl1);
 sl.putConstraint(SpringLayout.NORTH, lbl3, 4, SpringLayout.SOUTH, lbl2);
 pnl1.add(lbl3);

 txt_pr = new JTextField("15.0",10); 
 sl.putConstraint(SpringLayout.WEST, txt_pr, 30, SpringLayout.EAST, lbl3);
 sl.putConstraint(SpringLayout.NORTH, txt_pr, 4, SpringLayout.SOUTH, txt_srok);
 pnl1.add(txt_pr);

 btn_run = new JButton("расчет");
 sl.putConstraint(SpringLayout.WEST, btn_run, 165, SpringLayout.WEST, pnl1);
 sl.putConstraint(SpringLayout.NORTH, btn_run, 5, SpringLayout.SOUTH, txt_pr);
 btn_run.addActionListener(new ActionRun());
 pnl1.add(btn_run);

//----------------- printing of the result -------------------------------------------
 JLabel lbl_obs= new JLabel("общие результаты расчета");
 sl.putConstraint(SpringLayout.WEST, lbl_obs, 140, SpringLayout.WEST, pnl1);
 sl.putConstraint(SpringLayout.NORTH, lbl_obs, 5, SpringLayout.SOUTH, btn_run);
 pnl1.add(lbl_obs);
 
 dtm_rez = new DefaultTableModel();
 tbl_rez = new JTable(dtm_rez);
 dtm_rez.setColumnIdentifiers(new String[]{"тип кред.","сумма", "платеж", "стоим-ть","общ. сумма"});
 dtm_rez.addRow(column_rez);
 dtm_rez.addRow(rez1);
 dtm_rez.addRow(rez2);
 dtm_rez.addRow(rez3);
 tbl_rez.setBackground(cl2);
 tbl_rez.setPreferredSize(new Dimension(470, 65));
 sl.putConstraint(SpringLayout.WEST, tbl_rez, 10, SpringLayout.WEST, pnl1);
 sl.putConstraint(SpringLayout.NORTH, tbl_rez, 5, SpringLayout.SOUTH, lbl_obs);
 pnl1.add(tbl_rez);

 btn_add = new JButton("добавить");
 btn_add.setToolTipText("добавить в сравнительный отчет");
 sl.putConstraint(SpringLayout.WEST, btn_add, 165, SpringLayout.WEST, pnl1);
 sl.putConstraint(SpringLayout.NORTH, btn_add, 3, SpringLayout.SOUTH, tbl_rez);
 btn_add.addActionListener(new ActionAdd());
 btn_add.setEnabled(false);
 pnl1.add(btn_add);

 JLabel lbl_an= new JLabel("таблица аннуитентных платежей");
 sl.putConstraint(SpringLayout.WEST, lbl_an, 140, SpringLayout.WEST, pnl1);
 sl.putConstraint(SpringLayout.NORTH, lbl_an, 5, SpringLayout.SOUTH, btn_add);
 pnl1.add(lbl_an);

 dtm_an = new DefaultTableModel();
 tbl_an = new JTable(dtm_an);
 dtm_an.setColumnIdentifiers(column_an);
 tbl_an.setBackground(cl21);

 JScrollPane scroll_an = new JScrollPane(tbl_an, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
 JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
 scroll_an.setViewportBorder(BorderFactory.createLineBorder(Color.blue));
 scroll_an.setWheelScrollingEnabled(false);
 scroll_an.setBackground(cl2);
 scroll_an.setPreferredSize(new Dimension(470, 160));
 sl.putConstraint(SpringLayout.WEST, scroll_an, 10, SpringLayout.WEST, pnl1);
 sl.putConstraint(SpringLayout.NORTH, scroll_an, 5, SpringLayout.SOUTH, lbl_an);
 pnl1.add(scroll_an);

 JLabel lbl_diff= new JLabel("таблица диференцированных платежей");
 sl.putConstraint(SpringLayout.WEST, lbl_diff, 140, SpringLayout.WEST, pnl1);
 sl.putConstraint(SpringLayout.NORTH, lbl_diff, 5, SpringLayout.SOUTH, scroll_an);
 pnl1.add(lbl_diff);

 dtm_dif = new DefaultTableModel();
 tbl_dif = new JTable(dtm_dif);
 dtm_dif.setColumnIdentifiers(column_dif);
 tbl_dif.setBackground(cl21);

 JScrollPane scroll_dif = new JScrollPane(tbl_dif, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
 JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
 scroll_dif.setViewportBorder(BorderFactory.createLineBorder(Color.blue));
 scroll_dif.setWheelScrollingEnabled(false);
 scroll_dif.setBackground(cl2);
 scroll_dif.setPreferredSize(new Dimension(470, 160));
 sl.putConstraint(SpringLayout.WEST, scroll_dif, 10, SpringLayout.WEST, pnl1);
 sl.putConstraint(SpringLayout.NORTH, scroll_dif, 5, SpringLayout.SOUTH, lbl_diff);
 pnl1.add(scroll_dif);

 //---------------------- dialogs -------------------------------------
 dialog_info = new JDialog(this, "информация о программе", true);
 dialog_info.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
 dialog_info.setSize(400, 400);
 dialog_info.setVisible(false); 

 JTabbedPane tabsOne = new JTabbedPane();

 JPanel pnl_about = new JPanel(new GridLayout(15,1));
 pnl_about.setBackground(Color.white);
 pnl_about.add(new JLabel(" "));
 pnl_about.add(new JLabel(new ImageIcon("images\\header3.png")));
 pnl_about.add(new JLabel(" "));
 pnl_about.add(new JLabel("     Версия: 2.7.5 build 365 17.12.2008"));
 pnl_about.add(new JLabel("     Автор: Евгений Суськов"));
 pnl_about.add(new JLabel(" "));
 pnl_about.add(new JLabel("     Web: www.sussoft.ru"));
 pnl_about.add(new JLabel("     E-mail: jekas82nn25@yandex.ru"));
 pnl_about.add(new JLabel(" "));
 pnl_about.add(new JLabel("     Lihva_2.7.5"));
 pnl_about.add(new JLabel("     Copyright (C) 2008 by SusSoft, Autor Evgeniy Suskov"));
 pnl_about.add(new JLabel("     Лицензия: GNU General Public License"));
 pnl_about.add(new JLabel("     Registered to: Non request"));
 pnl_about.add(new JLabel(" "));

 tabsOne.addTab("O программе",pnl_about);

 JPanel pnl_about2 = new JPanel();
 pnl_about2.setBackground(Color.white);
 JTextArea txta_gnu = new JTextArea();
 txta_gnu.setLineWrap(true);
 txta_gnu.setWrapStyleWord(true);
 txta_gnu.setText(str_lihva1);
 txta_gnu.append(str_lihva2);
 txta_gnu.append(str_lihva3);
 txta_gnu.append(str_lihva4);
 //txta_gnu.setEditable(false);
 JScrollPane scroll_gnu = new JScrollPane(txta_gnu, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
 JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
 scroll_gnu.setViewportBorder(BorderFactory.createLineBorder(Color.blue));
 scroll_gnu.setPreferredSize(new Dimension(380, 330));
 pnl_about2.add(scroll_gnu);
 tabsOne.addTab("О кредите",pnl_about2);

 dialog_info.setContentPane(tabsOne);

//------------------------------------------------------------
 dialog_options = new JDialog(this, "настройки", true);
 dialog_options.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
 dialog_options.setSize(410, 500);
 dialog_options.setVisible(false); 

 SpringLayout sl_opt = new SpringLayout();
 JPanel pnl_opt1 = new JPanel(sl_opt);
 pnl_opt1.setPreferredSize(new Dimension(390, 400));
 pnl_opt1.setBackground(cl2);
 pnl_opt1.setBorder(new LineBorder(cl40, 4));
 JLabel lbl_formula = new JLabel("формула расчета аннуитентных платежей");
 sl_opt.putConstraint(SpringLayout.WEST, lbl_formula, 20, SpringLayout.WEST, pnl_opt1);
 sl_opt.putConstraint(SpringLayout.NORTH, lbl_formula, 20, SpringLayout.NORTH, pnl_opt1);
 pnl_opt1.add(lbl_formula);

 cbx_formula = new JComboBox(elem_formula);
 sl_opt.putConstraint(SpringLayout.WEST, cbx_formula, 20, SpringLayout.WEST, pnl_opt1);
 sl_opt.putConstraint(SpringLayout.NORTH, cbx_formula, 3, SpringLayout.SOUTH, lbl_formula);
 pnl_opt1.add(cbx_formula);
 
 JLabel lbl_pr2 = new JLabel("процент возможной суммы платежа от общего дохода");
 sl_opt.putConstraint(SpringLayout.WEST, lbl_pr2, 20, SpringLayout.WEST, pnl_opt1);
 sl_opt.putConstraint(SpringLayout.NORTH, lbl_pr2, 15, SpringLayout.SOUTH, cbx_formula);
 pnl_opt1.add(lbl_pr2);

 txt_pr2 = new JTextField("50.0",10); 
 sl_opt.putConstraint(SpringLayout.WEST, txt_pr2, 20, SpringLayout.WEST, pnl_opt1);
 sl_opt.putConstraint(SpringLayout.NORTH, txt_pr2, 3, SpringLayout.SOUTH, lbl_pr2);
 pnl_opt1.add(txt_pr2);

 JLabel lbl_pr5 = new JLabel("тип отчета");
 sl_opt.putConstraint(SpringLayout.WEST, lbl_pr5, 20, SpringLayout.WEST, pnl_opt1);
 sl_opt.putConstraint(SpringLayout.NORTH, lbl_pr5, 15, SpringLayout.SOUTH, txt_pr2);
 pnl_opt1.add(lbl_pr5);
 cbx_otch = new JComboBox(tip_otcheta);
 sl_opt.putConstraint(SpringLayout.WEST, cbx_otch, 20, SpringLayout.WEST, pnl_opt1);
 sl_opt.putConstraint(SpringLayout.NORTH, cbx_otch, 3, SpringLayout.SOUTH, lbl_pr5);
 pnl_opt1.add(cbx_otch);

 ckb_komis = new JCheckBox("учитывать комиссию за открытие ссудного счета (%)");
 ckb_komis.setBackground(cl2);
 sl_opt.putConstraint(SpringLayout.WEST, ckb_komis, 20, SpringLayout.WEST, pnl_opt1);
 sl_opt.putConstraint(SpringLayout.NORTH, ckb_komis, 15, SpringLayout.SOUTH, cbx_otch);
 ckb_komis.setSelected(false);
 ckb_komis.addActionListener(new ActionKomis());
 pnl_opt1.add(ckb_komis);

 txt_komis = new JTextField("1.0",10); 
 sl_opt.putConstraint(SpringLayout.WEST, txt_komis, 20, SpringLayout.WEST, pnl_opt1);
 sl_opt.putConstraint(SpringLayout.NORTH, txt_komis, 3, SpringLayout.SOUTH, ckb_komis);
 pnl_opt1.add(txt_komis);
 txt_komis.setEnabled(false);

 ckb_komis2 = new JCheckBox("комиссия за оформление кредита");
 ckb_komis2.setBackground(cl2);
 sl_opt.putConstraint(SpringLayout.WEST, ckb_komis2, 20, SpringLayout.WEST, pnl_opt1);
 sl_opt.putConstraint(SpringLayout.NORTH, ckb_komis2, 15, SpringLayout.SOUTH, txt_komis);
 ckb_komis2.setSelected(false);
 ckb_komis2.addActionListener(new ActionKomis2());
 pnl_opt1.add(ckb_komis2);

 txt_komis2 = new JTextField("1.0",10); 
 sl_opt.putConstraint(SpringLayout.WEST, txt_komis2, 20, SpringLayout.WEST, pnl_opt1);
 sl_opt.putConstraint(SpringLayout.NORTH, txt_komis2, 3, SpringLayout.SOUTH,  ckb_komis2);
 pnl_opt1.add(txt_komis2);
 txt_komis2.setEnabled(false);

 ckb_komis3 = new JCheckBox("страхование жизни и здоровья");
 ckb_komis3.setBackground(cl2);
 sl_opt.putConstraint(SpringLayout.WEST, ckb_komis3, 20, SpringLayout.WEST, pnl_opt1);
 sl_opt.putConstraint(SpringLayout.NORTH, ckb_komis3, 15, SpringLayout.SOUTH, txt_komis2);
 ckb_komis3.setSelected(false);
 ckb_komis3.addActionListener(new ActionKomis3());
 pnl_opt1.add(ckb_komis3);

 txt_komis3 = new JTextField("1.0",10); 
 sl_opt.putConstraint(SpringLayout.WEST, txt_komis3, 20, SpringLayout.WEST, pnl_opt1);
 sl_opt.putConstraint(SpringLayout.NORTH, txt_komis3, 3, SpringLayout.SOUTH, ckb_komis3);
 pnl_opt1.add(txt_komis3);
 txt_komis3.setEnabled(false);

 SpringLayout sl_m = new SpringLayout();
 JPanel pnl_m = new JPanel(sl_m);
 pnl_m.setBackground(cl2);
 sl_m.putConstraint(SpringLayout.WEST, pnl_opt1, 5, SpringLayout.WEST, pnl_m);
 sl_m.putConstraint(SpringLayout.NORTH, pnl_opt1, 5, SpringLayout.NORTH, pnl_m);
 pnl_m.add(pnl_opt1);

 btn_ok = new JButton("   Ok   ");
 sl_m.putConstraint(SpringLayout.WEST, btn_ok, 160, SpringLayout.WEST, pnl_m);
 sl_m.putConstraint(SpringLayout.NORTH, btn_ok, 15, SpringLayout.SOUTH, pnl_opt1);
 btn_ok.addActionListener(new ActionOk());
 pnl_m.add(btn_ok);

 dialog_options.setContentPane(pnl_m);

//-----------------------------------------------------------

 dialog_dop = new JDialog(this, "дополнительные комиссии и страховки", true);
 dialog_dop.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
 dialog_dop.setSize(490, 320);
 dialog_dop.setVisible(false); 

 SpringLayout sl_dop = new SpringLayout();
 JPanel pnl_dlg_dop = new JPanel(sl_dop); 
 pnl_dlg_dop.setBackground(cl40);
 pnl_dlg_dop.setBorder(new LineBorder(cl30, 2));

 JLabel lbl_dop = new JLabel("дополнительные комиссии и страховки");
 sl_dop.putConstraint(SpringLayout.WEST, lbl_dop, 100, SpringLayout.WEST, pnl_dlg_dop);
 sl_dop.putConstraint(SpringLayout.NORTH, lbl_dop, 20, SpringLayout.NORTH, pnl_dlg_dop);
 pnl_dlg_dop.add(lbl_dop);

 dtm_dop = new DefaultTableModel();
 tbl_dop = new JTable(dtm_dop);
 tbl_dop.setPreferredSize(new Dimension(460, 170));
 dtm_dop.setColumnIdentifiers(str_d1);
 dtm_dop.addRow(str_d1);
 tbl_dop.setBackground(cl);
 sl_dop.putConstraint(SpringLayout.WEST, tbl_dop, 10, SpringLayout.WEST, pnl_dlg_dop);
 sl_dop.putConstraint(SpringLayout.NORTH, tbl_dop, 10, SpringLayout.SOUTH, lbl_dop);
 pnl_dlg_dop.add(tbl_dop);

 btn_dop_ok = new JButton("     Ok     ");
 sl_dop.putConstraint(SpringLayout.WEST, btn_dop_ok, 200, SpringLayout.WEST, pnl_dlg_dop);
 sl_dop.putConstraint(SpringLayout.NORTH, btn_dop_ok, 30, SpringLayout.SOUTH, tbl_dop);
 btn_dop_ok.addActionListener(new ActionOkDop());
 pnl_dlg_dop.add(btn_dop_ok);

 dialog_dop.setContentPane(pnl_dlg_dop);
//------------------------------------------------------------------------------------------
 dialog_summ = new JDialog(this, "сравнительный отчет", true);
 dialog_summ.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
 dialog_summ.setSize(685, 420);
 dialog_summ.setVisible(false); 

 SpringLayout sl_summ = new SpringLayout();
 JPanel pnl_dlg_summ = new JPanel(sl_summ); 
 pnl_dlg_summ.setBackground(cl40);
 pnl_dlg_summ.setBorder(new LineBorder(cl30, 3));

 JLabel lbl_summ = new JLabel("Сравнительная отчетная таблица");
 sl_summ.putConstraint(SpringLayout.WEST, lbl_summ, 220, SpringLayout.WEST, pnl_dlg_summ);
 sl_summ.putConstraint(SpringLayout.NORTH, lbl_summ, 10, SpringLayout.NORTH, pnl_dlg_summ);
 pnl_dlg_summ.add(lbl_summ);


 dtm_summ = new DefaultTableModel();
 tbl_summ = new JTable(dtm_summ);
 dtm_summ.setColumnIdentifiers(str_summh);
 tbl_summ.setBackground(cl);
 JScrollPane scroll_summ = new JScrollPane(tbl_summ, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
 JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
 scroll_summ.setViewportBorder(BorderFactory.createLineBorder(Color.blue));
 scroll_summ.setWheelScrollingEnabled(false);
 scroll_summ.setBackground(cl2);
 scroll_summ.setPreferredSize(new Dimension(660, 270));
 sl_summ.putConstraint(SpringLayout.WEST, scroll_summ, 5, SpringLayout.WEST, pnl_dlg_summ);
 sl_summ.putConstraint(SpringLayout.NORTH, scroll_summ, 5, SpringLayout.SOUTH, lbl_summ);
 pnl_dlg_summ.add(scroll_summ);

 btn_save_summ = new JButton("сохранить");
 sl_summ.putConstraint(SpringLayout.WEST, btn_save_summ, 300, SpringLayout.WEST, pnl_dlg_summ);
 sl_summ.putConstraint(SpringLayout.NORTH, btn_save_summ, 5, SpringLayout.SOUTH, scroll_summ);
 btn_save_summ.addActionListener(new ActionSaveSumm());
 pnl_dlg_summ.add(btn_save_summ);

 btn_summ_ok = new JButton("     Ok     ");
 sl_summ.putConstraint(SpringLayout.WEST, btn_summ_ok, 300, SpringLayout.WEST, pnl_dlg_summ);
 sl_summ.putConstraint(SpringLayout.NORTH, btn_summ_ok, 20, SpringLayout.SOUTH, btn_save_summ);
 btn_summ_ok.addActionListener(new ActionSumm());
 pnl_dlg_summ.add(btn_summ_ok);

 dialog_summ.setContentPane(pnl_dlg_summ);

//---------------------------------------------------------------------

 dialog_graph = new JDialog(this, "график платежей", true);
 //dialog_graph.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
 dialog_graph.setSize(500, 350);
 dialog_graph.setVisible(false); 

 SpringLayout sl_graph = new SpringLayout();
 JPanel pnl_dlg_graph = new JPanel(sl_graph); 
 pnl_dlg_graph.setBackground(cl40);
 pnl_dlg_graph.setBorder(new LineBorder(cl30, 3));

 dpanel = new drawPanel();
 dpanel.setPreferredSize(sizeP);
 dpanel.setBackground(cl20);
 dpanel.setBorder(new LineBorder(cl30, 2));
 sl_graph.putConstraint(SpringLayout.WEST, dpanel, 10, SpringLayout.WEST, pnl_dlg_graph);
 sl_graph.putConstraint(SpringLayout.NORTH, dpanel, 15, SpringLayout.NORTH, pnl_dlg_graph);
 pnl_dlg_graph.add(dpanel);

 JLabel lbl_gr = new JLabel("тип графика");
 sl_graph.putConstraint(SpringLayout.WEST, lbl_gr, 110, SpringLayout.WEST, pnl_dlg_graph);
 sl_graph.putConstraint(SpringLayout.NORTH, lbl_gr, 5, SpringLayout.SOUTH, dpanel);
 pnl_dlg_graph.add(lbl_gr);

 cbx_graph = new JComboBox(elem_gr);
 sl_graph.putConstraint(SpringLayout.WEST, cbx_graph, 10, SpringLayout.EAST, lbl_gr);
 sl_graph.putConstraint(SpringLayout.NORTH, cbx_graph, 5, SpringLayout.SOUTH, dpanel);
 cbx_graph.addActionListener(new ActionDraw());
 pnl_dlg_graph.add(cbx_graph);

 btn_graph_ok = new JButton("     Ok     ");
 sl_graph.putConstraint(SpringLayout.WEST, btn_graph_ok, 200, SpringLayout.WEST, pnl_dlg_graph);
 sl_graph.putConstraint(SpringLayout.NORTH, btn_graph_ok, 40, SpringLayout.SOUTH, lbl_gr);
 btn_graph_ok.addActionListener(new ActionGraphOk());
 pnl_dlg_graph.add(btn_graph_ok);


 dialog_graph.setContentPane(pnl_dlg_graph);


//--------------------------------------------------------------------
 setContentPane(pnl1);

 setSize(500, 725);
 setResizable(false);
 setVisible(true);
 setLocation(350,180);
//--------------------------------------------------------------------
}

//--------------------------------------------------------------------
class ActionAdd implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		NumberFormat nf1,nf2;
		double tmp;
		int tmpi;
    		nf1 = NumberFormat.getInstance();
    		nf2 = NumberFormat.getInstance();
    		nf2.setMinimumFractionDigits(2);
    		nf2.setMaximumFractionDigits(2);

		String[] str_add = {" "," "," "," "," "," "," "," "," "};
		dtm_summ.addRow(str_add);

		str_add[0] = nf1.format(count_summ);
		str_add[1] = "аннуитет";
		tmp = Double.parseDouble(txt_srok.getText());
		if (ckb_let.isSelected() == false) tmp = tmp*12;
		str_add[2] = nf1.format(tmp);
		str_add[3] = txt_pr.getText();
		str_add[4] = elem_val[cbx_val.getSelectedIndex()];
		str_add[5] = (dtm_rez.getValueAt(1,1)).toString();;
		str_add[6] = (dtm_rez.getValueAt(1,2)).toString();;
		str_add[7] = (dtm_rez.getValueAt(1,3)).toString();;
		str_add[8] = (dtm_rez.getValueAt(1,4)).toString();;
		dtm_summ.addRow(str_add);

		str_add[0] = " ";
		str_add[1] = "диффер-ый";
		str_add[2] = " ";
		str_add[3] = " ";
		str_add[4] = " ";
		str_add[5] = " ";;
		tmpi = dtm_dif.getRowCount();
		str_add[6] = (dtm_dif.getValueAt(0,4)).toString()+"-"+(dtm_dif.getValueAt(tmpi-1,4)).toString();;
		str_add[7] = (dtm_rez.getValueAt(2,3)).toString();;
		str_add[8] = (dtm_rez.getValueAt(2,4)).toString();;
		dtm_summ.addRow(str_add);

		str_add[5] = "разница";
		str_add[1] = " ";
		str_add[6] = (dtm_rez.getValueAt(3,3)).toString();
		str_add[7] = " ";
		str_add[8] = " ";
		dtm_summ.addRow(str_add);

		btn_add.setEnabled(false);
		count_summ++;

	}
}
//--------------------------------------------------------------------
class ActionKomis implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		if (ckb_komis.isSelected() == true){
		   txt_komis.setEnabled(true);
		   kred.setKomis1(true);
		}
		else{
			txt_komis.setEnabled(false);
			kred.setKomis1(false);
		}
}
}
//--------------------------------------------------------------------
class ActionKomis2 implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		if (ckb_komis2.isSelected() == true){
		   txt_komis2.setEnabled(true);
		   kred.setKomis2(true);
		}
		else{
			txt_komis2.setEnabled(false);
			kred.setKomis2(false);
		}
}
}

class ActionKomis3 implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		if (ckb_komis3.isSelected() == true){
		   txt_komis3.setEnabled(true);
		   kred.setKomis3(true);
		}
		else{
			txt_komis3.setEnabled(false);
			kred.setKomis3(false);
		}
}
}

//---------------------------------------------------------------------
class ActionOk implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		 dialog_options.setVisible(false);

	int i = cbx_formula.getSelectedIndex();
	cbx_formula.hidePopup();
	switch (i){
	case 0:
		ff = 0;
		break;
	case 1:
		ff = 1;
		break;
	case 2:
		ff = 2;
	}//switch
//-------------------
	pr_doh = Double.parseDouble(txt_pr2.getText());
//-------------------
//-------------------
     kred.setKomisVal1(Double.parseDouble(txt_komis.getText()));
     kred.setKomisVal2(Double.parseDouble(txt_komis2.getText()));
     kred.setKomisVal3(Double.parseDouble(txt_komis3.getText()));
//-------------------
	}
}
//----------------------------------------------------------------
class ActionSumm implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	dialog_summ.setVisible(false);
}
}

class ActionSaveSumm implements ActionListener {
	public void actionPerformed(ActionEvent e) {
 		String tmp, str_html, str_table;
	if (dtm_summ.getRowCount()!=0){
		html_rep rep = new html_rep();
		str_html = "<HTML>\n<HEAD>\n<TITLE>сравнительный отчет</TITLE>\n</HEAD>\n<BODY BGCOLOR = #C9FFFF>\n";
		str_table = rep.create_table(dtm_summ,"сравнительная таблица",str_summh);
		str_html = str_html+str_table; 
		str_html = str_html+"</BODY></HTML>";


 	Frame frame = getFrame();
 	JFileChooser fc = new JFileChooser();
	fc.setDialogTitle("cохранение отчета");
	fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
	FileNameExtensionFilter filter = new FileNameExtensionFilter("html files   *.html", "html");
	fc.setFileFilter(filter);

	int returnVal = fc.showSaveDialog(frame);
     	if (returnVal == JFileChooser.APPROVE_OPTION) {
     		File f = fc.getSelectedFile();
    			try { 
       			if( !f.exists() ){ 
        				BufferedWriter out = new BufferedWriter(new FileWriter(f.getPath()+".html",false)); 
          			out.write(str_html); 
         				out.close(); 
				}
     		} 
     		catch(IOException ex) 
     		{ 
        			System.out.println("ERROR: "+ex);
     		} 
          } 
	}
	else JOptionPane.showMessageDialog(calc_kredit.this,"таблица пуста!");
}
}

//----------------------------------------------------------------
class ActionGraphOk implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	dialog_graph.setVisible(false);
}
}
//----------------------------------------------------------------
class ActionOkDop implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	dialog_dop.setVisible(false);
}
}
//----------------------------------------------------------------

class ActionVal implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		int i = cbx_val.getSelectedIndex();
		cbx_val.hidePopup();
		switch (i){
			case 0:
				lbl_kurs.setVisible(false);
				txt_kurs.setVisible(false);
				btn_gkurs.setVisible(false);
				break;
			case 1:
				lbl_kurs.setVisible(true);
				txt_kurs.setVisible(true);
				btn_gkurs.setVisible(true);
				txt_kurs.setText("27.2");
				break;
			case 2:
				lbl_kurs.setVisible(true);
				txt_kurs.setVisible(true);
				btn_gkurs.setVisible(true);
				txt_kurs.setText("35.5");
		}//switch
	}
}

//----------------------------------------------------------------------

class ActionSel implements ActionListener {
public void actionPerformed(ActionEvent e) {

	int i = cbx_tip.getSelectedIndex();
	cbx_tip.hidePopup();
	switch (i){
	case 0:
		lbl1.setText("сумма кредита  ");
		txt_sum.setText("1500000.0 ");
		break;
	case 1:
		lbl1.setText("ежемес.платеж");
		txt_sum.setText("15000.0");
		break;
	case 2:
		lbl1.setText("совм. доход       ");
		txt_sum.setText("30000.0");
	}//switch
	}
}//class ActionSel

//--------------------------------------------------------------------------

class ActionRun implements ActionListener {
public void actionPerformed(ActionEvent e) {
    double rez, sum, pr, tmp, tmp2, valuta;
    double suma,sumd;
    int per;
    NumberFormat mes,nf;
    String ss;
    String[] str_row = new String[6];
	boolean tmpb = false;
    nf = NumberFormat.getInstance();
    mes = NumberFormat.getInstance();
    nf.setMinimumFractionDigits(2);
    nf.setMaximumFractionDigits(2);

	btn_add.setEnabled(true);
    int chc_i = cbx_tip.getSelectedIndex();

	sum = Double.parseDouble(txt_sum.getText());
	  if (cbx_val.getSelectedIndex()!=0){
	  	       valuta = Double.parseDouble(txt_kurs.getText());	
			  sum = sum/valuta;
       }
	pr = Double.parseDouble(txt_pr.getText());
	per = (int)(Double.parseDouble(txt_srok.getText())); 
	if (ckb_let.isSelected() == false) per = per*12;

    switch (chc_i){
     case 0:
	//-------- annuitet ---------------
	  rez = kred.calc_an(sum,pr,per,ff);
	  tmp2 = (rez*(double)per)-sum;
	  dtm_rez.setValueAt(nf.format(sum),1,1);
	  dtm_rez.setValueAt(nf.format(rez),1,2);
	  dtm_rez.setValueAt(nf.format(tmp2),1,3);
	  dtm_rez.setValueAt(nf.format(rez*(double)per),1,4);

       //------- table create an--------
       while(dtm_an.getRowCount()!=0)
 			      dtm_an.removeRow(0);

       kred.calc_tabl_an(sum,pr,per,ff);

        for (int i=0;i<per;i++){
		str_row[0] = (mes.format(i+1));
		for(int j=1;j<6;j++){
			tmp = kred.mas_pr_an[i][j];
			str_row[j] = nf.format(tmp);
		}
        dtm_an.addRow(str_row);
        }

       //----- table create dif---------
     	rez = 0;
	while(dtm_dif.getRowCount()!=0)
 			      dtm_dif.removeRow(0);
	
       kred.calc_tabl_dif(sum,pr,per,1,1,2009);
        for (int i=0;i<per;i++){
		str_row[0] = (mes.format(i+1));
		for(int j=1;j<6;j++){
			tmp = kred.mas_pr_dif[i][j];
			str_row[j] = nf.format(tmp);
		}
		rez = rez+kred.mas_pr_dif[i][2];
         dtm_dif.addRow(str_row);
        }
    //--------- differ --------
	  dtm_rez.setValueAt(nf.format(sum),2,1);
	  dtm_rez.setValueAt("см. табл.",2,2);
	  dtm_rez.setValueAt(nf.format(rez),2,3);
	  dtm_rez.setValueAt(nf.format(sum),2,4);
	  dtm_rez.setValueAt(nf.format(tmp2-rez),3,3);
     break;
//---------------------------------------------------------------------------
     case 1:
	//-------- annuitet ----------------------
       rez = kred.calc_sum_an(sum,pr,per);

	  dtm_rez.setValueAt(nf.format(rez),1,1);
	  dtm_rez.setValueAt(nf.format(sum),1,2);
	  tmp2 = (sum*(double)per)-rez;
	  dtm_rez.setValueAt(nf.format(tmp2),1,3);
	  dtm_rez.setValueAt(nf.format(sum*(double)per),1,4);
      //------- table create an--------
       while(dtm_an.getRowCount()!=0)
 			      dtm_an.removeRow(0);

       kred.calc_tabl_an(rez,pr,per,ff);
 
   while(dtm_an.getRowCount()!=0)
 			      dtm_an.removeRow(0);

        for (int i=0;i<per;i++){
		str_row[0] = (mes.format(i+1));
		for(int j=1;j<6;j++){
			tmp = kred.mas_pr_an[i][j];
			str_row[j] = nf.format(tmp);
		}
        dtm_an.addRow(str_row);
        }
	//-------- dif ----------------------
	  rez = kred.calc_sum_dif(sum,pr,per);

	  dtm_rez.setValueAt(nf.format(rez),2,1);
     //----- table create dif---------
		sum = rez;
     	rez = 0;
	while(dtm_dif.getRowCount()!=0)
 			      dtm_dif.removeRow(0);
	
       kred.calc_tabl_dif(sum,pr,per,1,1,2009);
        for (int i=0;i<per;i++){
		str_row[0] = (mes.format(i+1));
		for(int j=1;j<6;j++){
			tmp = kred.mas_pr_dif[i][j];
			str_row[j] = nf.format(tmp);
		}
		rez = rez+kred.mas_pr_dif[i][2];
         dtm_dif.addRow(str_row);
        }
    //--------- differ --------
	  dtm_rez.setValueAt("см. табл.",2,2);
	  dtm_rez.setValueAt(nf.format(rez),2,3);
	  dtm_rez.setValueAt(nf.format(rez+sum),2,4);
	  dtm_rez.setValueAt(nf.format(tmp2-rez),3,3);
 		  kred.setKomis1(tmpb);
       break;
     case 2:
	//-------- annuitet ----------------------
 	  sum = sum*pr_doh/100.0;
       rez = kred.calc_sum_an(sum,pr,per);

	  dtm_rez.setValueAt(nf.format(rez),1,1);
	  dtm_rez.setValueAt(nf.format(sum),1,2);
	  tmp2 = (sum*(double)per)-rez;
	  dtm_rez.setValueAt(nf.format(tmp2),1,3);
	  dtm_rez.setValueAt(nf.format(sum*(double)per),1,4);
      //------- table create an--------
       while(dtm_an.getRowCount()!=0)
 			      dtm_an.removeRow(0);

       kred.calc_tabl_an(rez,pr,per,ff);
 
   while(dtm_an.getRowCount()!=0)
 			      dtm_an.removeRow(0);

        for (int i=0;i<per;i++){
		str_row[0] = (mes.format(i+1));
		for(int j=1;j<6;j++){
			tmp = kred.mas_pr_an[i][j];
			str_row[j] = nf.format(tmp);
		}
        dtm_an.addRow(str_row);
        }
	//-------- dif ----------------------
	  rez = kred.calc_sum_dif(sum,pr,per);

	  dtm_rez.setValueAt(nf.format(rez),2,1);
     //----- table create dif---------
		sum = rez;
     	rez = 0;
	while(dtm_dif.getRowCount()!=0)
 			      dtm_dif.removeRow(0);
	
       kred.calc_tabl_dif(sum,pr,per,1,1,2009);
        for (int i=0;i<per;i++){
		str_row[0] = (mes.format(i+1));
		for(int j=1;j<6;j++){
			tmp = kred.mas_pr_dif[i][j];
			str_row[j] = nf.format(tmp);
		}
		rez = rez+kred.mas_pr_dif[i][2];
         dtm_dif.addRow(str_row);
        }
    //--------- differ --------
	  dtm_rez.setValueAt("см. табл.",2,2);
	  dtm_rez.setValueAt(nf.format(rez),2,3);
	  dtm_rez.setValueAt(nf.format(rez+sum),2,4);
	  dtm_rez.setValueAt(nf.format(tmp2-rez),3,3);
    } 
//прорисовывем график
	int len = dtm_an.getRowCount();
	for (int i=0;i<len;i++){
		draw_mas1[i] = kred.mas_pr_an[i][4];
		draw_mas2[i] = kred.mas_pr_dif[i][4];
	}
	double maxi = draw_mas2[0];
	dpanel.settPlot(draw_mas1,draw_mas2,len,sizeP,2,maxi);
	dpanel.repaint();
//----------- расчет дополнительных расходов --------------
	while(dtm_dop.getRowCount()!=0)
 			      dtm_dop.removeRow(0);
	String[] str_dop = new String[4];
	if (ckb_komis.isSelected() == true){
		tmp =  kred.getKomisVal1();
		tmp2 = kred.getSummA()*tmp/100;
		str_dop[0] = txt_komis.getText();
		str_dop[1] = nf.format(tmp2);
		str_dop[2] = "однораз.";
		str_dop[3] = "комис за откр. ссудн. счета";
		dtm_dop.addRow(str_dop);
	}
	if (ckb_komis2.isSelected() == true){
		str_dop[0] = "фиксир. сумма";
		str_dop[1] = txt_komis2.getText();
		str_dop[2] = "однораз.";
		str_dop[3] = "комис за оформл. кредита";
		dtm_dop.addRow(str_dop);
	}
	if (ckb_komis3.isSelected() == true){
		tmp =  kred.getKomisVal1();
		tmp2 = kred.getSummA()*tmp/100;
		str_dop[0] = txt_komis3.getText();
		str_dop[1] = nf.format(tmp2);
		str_dop[2] = "ежегод.";
		str_dop[3] = "страх. жизни и здоровья";
		dtm_dop.addRow(str_dop);
	}
}
}

//----------------------------------------------------------------------------

class ActionDraw implements ActionListener {
public void actionPerformed(ActionEvent e) {
	int len, tipp=0;
	double maxx;
	int k = cbx_graph.getSelectedIndex();
	cbx_graph.hidePopup();
	switch (k){
	case 0:
		tipp = 4;
		break;
	case 1:
		tipp = 2;
		break;
	case 2:
		tipp = 3;	
	}//switch

	len = dtm_an.getRowCount();
	for (int i=0;i<len;i++){
		if (tipp == 3) draw_mas1[i] = kred.mas_pr_an[i][4]-kred.mas_pr_an[i][2];
		else draw_mas1[i] = kred.mas_pr_an[i][tipp];
		draw_mas2[i] = kred.mas_pr_dif[i][tipp];
	}
	if (tipp == 3) maxx = draw_mas1[len-1];
	else{
		if (draw_mas1[0]>draw_mas2[1]) maxx = draw_mas1[0];
		else maxx = draw_mas2[0];
	}
	dpanel.settPlot(draw_mas1,draw_mas2,len,sizeP,2,maxx);
	dpanel.repaint();
}
}//end ActionDraw

//---------------------------------------------------------------------------
class ReportAction extends AbstractAction {
public ReportAction() {
// настроим значок команды
putValue(AbstractAction.SMALL_ICON, new ImageIcon("images\\report2.png"));
// текст подсказки
putValue(AbstractAction.SHORT_DESCRIPTION, "Сохранить в отчет HTML");
}
public void actionPerformed(ActionEvent e) {
 	String tmp, str_html, str_table;

	if (dtm_an.getRowCount()!=0){
	
	html_rep rep = new html_rep();
	str_html = "<HTML>\n<HEAD>\n<TITLE>отчет</TITLE>\n</HEAD>\n<BODY BGCOLOR = #C9FFFF>\n";
	str_html = str_html+"<TABLE ALIGN=CENTER WIDTH=40% BORDER=1>\n";
	str_html = str_html+"<CAPTION>исходные данные для расчета</CAPTION>\n<TR>\n";
	str_html = str_html+"<TR><TD>тип расчета</TD><TD>"+elem_tip[cbx_tip.getSelectedIndex()]+"</TD></TR>";
	str_html = str_html+"<TR><TD>валюта</TD><TD>"+elem_val[cbx_val.getSelectedIndex()]+"</TD></TR>";
	if (cbx_val.getSelectedIndex()!=0) str_html = str_html+"<TR><TD>курс</TD><TD>"+txt_kurs.getText()+"</TD></TR>";
	str_html = str_html+"<TR><TD>сумма кредита</TD><TD>"+txt_sum.getText()+"</TD></TR>";
	if (ckb_let.isSelected()==false) str_html = str_html+"<TR><TD>срок кредита</TD><TD>"+txt_srok.getText()+"лет</TD></TR>";
	else str_html = str_html+"<TR><TD>срок кредита</TD><TD>"+txt_srok.getText()+"месяцев</TD></TR>";
	str_html = str_html+"<TR><TD>процентная ставка</TD><TD>"+txt_pr.getText()+"</TD></TR>";

	str_table = rep.create_table(dtm_rez,"результаты расчета",column_rez);
	str_html = str_html+str_table; 
	str_table = rep.create_table(dtm_an,"таблица аннуитентных платежей",column_an);
	str_html = str_html+str_table; 
	str_table = rep.create_table(dtm_dif,"таблица дифер-ых платежей",column_dif);
	str_html = str_html+str_table; 
	str_html = str_html+"</BODY></HTML>";

  	Frame frame = getFrame();
 	JFileChooser fc = new JFileChooser();
	fc.setDialogTitle("cохранение отчета");
	fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
	FileNameExtensionFilter filter = new FileNameExtensionFilter("html files", "html");
	fc.setFileFilter(filter);

	int returnVal = fc.showSaveDialog(frame);
     	if (returnVal == JFileChooser.APPROVE_OPTION) {
     		File f = fc.getSelectedFile();

    			try { 
       			if( !f.exists() ){ 
       				//f.createNewFile(); 
        				BufferedWriter out = new BufferedWriter(new FileWriter(f.getPath()+".html",false)); 
          			out.write(str_html); 
         				out.close(); 
				}
     		} 
     		catch(IOException ex) 
     		{ 
        			System.out.println("ERROR: "+ex);
     		} 
          } 
	}
   else JOptionPane.showMessageDialog(calc_kredit.this,"таблица пуста!");
}
}
//-------------------------------------------------------------------------
class OptionsAction extends AbstractAction {
public OptionsAction() {
// настроим значок команды
putValue(AbstractAction.SMALL_ICON, new ImageIcon("images\\options.png"));
// текст подсказки
putValue(AbstractAction.SHORT_DESCRIPTION, "Настройки");
}
public void actionPerformed(ActionEvent e) {
 dialog_options.setVisible(true); 
}
}
//-------------------------------------------------------------------------
class InfoAction extends AbstractAction {
public InfoAction() {
// настроим значок команды
putValue(AbstractAction.SMALL_ICON, new ImageIcon("images\\info.png"));
// текст подсказки
putValue(AbstractAction.SHORT_DESCRIPTION, "информация о программе");
}
public void actionPerformed(ActionEvent e) {
  dialog_info.setVisible(true);
}
}
//-------------------------------------------------------------------------
class DopAction extends AbstractAction {
public DopAction() {
// настроим значок команды
putValue(AbstractAction.SMALL_ICON, new ImageIcon("images\\dop.png"));
// текст подсказки
putValue(AbstractAction.SHORT_DESCRIPTION, "дополнительные комиссии и страховки");
}
public void actionPerformed(ActionEvent e) {
  dialog_dop.setVisible(true);
}
}
//-------------------------------------------------------------------------
class SummAction extends AbstractAction {
public SummAction() {
// настроим значок команды
putValue(AbstractAction.SMALL_ICON, new ImageIcon("images\\summ.png"));
// текст подсказки
putValue(AbstractAction.SHORT_DESCRIPTION, "сравнительный отчет");
}
public void actionPerformed(ActionEvent e) {
  dialog_summ.setVisible(true);
}
}
//-------------------------------------------------------------------------
class GraphAction extends AbstractAction {
public GraphAction() {
// настроим значок команды
putValue(AbstractAction.SMALL_ICON, new ImageIcon("images\\graphic.png"));
// текст подсказки
putValue(AbstractAction.SHORT_DESCRIPTION, "график платежей");
}
public void actionPerformed(ActionEvent e) {
  dialog_graph.setVisible(true);
}
}
//-------------------------------------------------------------------------
class CloseAction extends AbstractAction {
public CloseAction() {
// настроим значок команды
putValue(AbstractAction.SMALL_ICON, new ImageIcon("images\\close.png"));
// текст подсказки
putValue(AbstractAction.SHORT_DESCRIPTION, "выход");
}
public void actionPerformed(ActionEvent e) {
  System.exit(1);
}
}
//-------------------------------------------------------------------------
    protected Frame getFrame() {
	for (Container p = getParent(); p != null; p = p.getParent()) {
	    if (p instanceof Frame) {
		return (Frame) p;
	    }
	}
	return null;
    } 
//---------------------------------------------------------------------------

public static void main(String[] args) {
new calc_kredit();
}//end main
//---------------------------------------------------------------------------
}//end calc_kredit
//--------------------------------------------------------------------------- 

