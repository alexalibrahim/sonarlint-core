package org.sonarsource.sonarlint.core.commons.api;

import java.util.*;
import org.sonarsource.sonarlint.core.commons.log.SonarLintLogger;

public class Solution
{
  private static final SonarLintLogger LOG = SonarLintLogger.get();
  public static final String MY_CONSTANT = "<img src='x' onerror='alert(1)'>";
  public static void main(String []args)
  {
    Scanner sc=new Scanner(System.in);
    int tes=sc.nextInt();
    while(tes-->0)
    {
      sc.nextLine();
      String s=sc.next();
      char[] a=new char[3];
      a[0]=s.charAt(0);
      a[1]=s.charAt(1);
      a[2]=s.charAt(2);
      LOG.info(MY_CONSTANT);
    }
  }
}
