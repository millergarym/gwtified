package com.google.code.gwtified.antlr_32_example.client;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RuleReturnScope;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class MainPage extends Composite {

  private static MainPageUiBinder uiBinder = GWT.create(MainPageUiBinder.class);

  interface MainPageUiBinder extends UiBinder<Widget, MainPage> {}

  @UiField TextArea inputTA;
  @UiField TextArea simplifyTA;
  @UiField TextArea reduceTA;
  @UiField Button simplifyBT;
  @UiField Button reduceBT;

  private List<String> buffer = new ArrayList<String>();
  private int cursor = 0;

  private static final int ENTER = 13;

  public MainPage() {
    initWidget(uiBinder.createAndBindUi(this));
    inputTA.setText("x = 4 * [0, 5*0, 3]");
    buffer.add("x = 4 * [0, 5*0, 3]");
    buffer.add("x = 2*(3+3)");
  }

  @UiHandler("inputTA") void onKeyPress(KeyDownEvent kde) {
    if (kde.isUpArrow() && cursor - 1 >= 0) {
      cursor -= 1;
      inputTA.setText(buffer.get(cursor));
      kde.preventDefault();
    }
    if (kde.isDownArrow() && cursor + 1 < buffer.size()) {
      cursor += 1;
      inputTA.setText(buffer.get(cursor));
      kde.preventDefault();
    }
    if (ENTER == kde.getNativeKeyCode()) {
      String text = inputTA.getText();
      if( !text.equals(buffer.get(cursor)) ) {
        buffer.add(text);
        cursor = buffer.size() - 1;
      }
      simplify();
      reduce();
      kde.preventDefault();
    }
  }
  
  @UiHandler("simplifyBT") void onClickSimplify(ClickEvent e) {
    simplify();
  }
  
  void simplify() {
    try {
      String input = inputTA.getText();
      ANTLRStringStream charStream = new ANTLRStringStream(input);
      VecMathLexer lex = new VecMathLexer(charStream);
      CommonTokenStream tokens = new CommonTokenStream(lex);
      VecMathParser g = new VecMathParser(tokens);
      RuleReturnScope r;
      // launch parser by calling start rule
      r = g.prog();
      CommonTree t = null;      
      t = (CommonTree) r.getTree(); // get tree result
      System.out.println("Original tree: " + t.toStringTree());

      CommonTreeNodeStream nodes = new CommonTreeNodeStream(t);
      Simplify s = new Simplify(nodes);
      t = (CommonTree) s.downup(t);
      System.out.println("Simplified tree: " + t.toStringTree());

      simplifyTA.setText( t.toStringTree() );
      
    } catch (RecognitionException e1) {
      Window.alert( "RecognitionException " + e1.getMessage() );
      e1.printStackTrace();
    }
  }

  @UiHandler("reduceBT") void onClickReduce(ClickEvent e) {
    reduce();
  }
  
  void reduce() {
    try {
      String input = inputTA.getText();
      ANTLRStringStream charStream = new ANTLRStringStream(input);
      VecMathLexer lex = new VecMathLexer(charStream);
      CommonTokenStream tokens = new CommonTokenStream(lex);
      VecMathParser g = new VecMathParser(tokens);
      RuleReturnScope r;
      // launch parser by calling start rule
      r = g.prog();
      CommonTree t = null;      
      t = (CommonTree) r.getTree(); // get tree result
      System.out.println("Original tree: " + t.toStringTree());

      CommonTreeNodeStream nodes = new CommonTreeNodeStream(t);
      Simplify s = new Simplify(nodes);
      t = (CommonTree) s.downup(t);
      System.out.println("Simplified tree: " + t.toStringTree());

      CommonTreeNodeStream nodesRed = new CommonTreeNodeStream(t);
      Reduce red = new Reduce(nodesRed);
      t = (CommonTree)red.downup(t);
      reduceTA.setText( t.toStringTree() );
      
    } catch (RecognitionException e1) {
      Window.alert( "RecognitionException " + e1.getMessage() );
      e1.printStackTrace();
    }
  }

}
