package tn.esprit.spring.entity;

import javax.persistence.Entity;


public class Simplex {

	public double[][] tableaux; // tableaux
    public int numberOfConstraints; // number of constraints
    public int numberOfOriginalVariables; // number of original variables
   
    public boolean maximizeOrMinimize;
   
    public static final boolean MAXIMIZE = true;
    public static final boolean MINIMIZE = false;
   
    private int[] basis; // basis[i] = basic variable corresponding to row i
   
    public Simplex(double[][] tableaux, int numberOfConstraint,
      int numberOfOriginalVariable, boolean maximizeOrMinimize) {
     this.maximizeOrMinimize = maximizeOrMinimize;
     this.numberOfConstraints = numberOfConstraint;
     this.numberOfOriginalVariables = numberOfOriginalVariable;
     this.tableaux = tableaux;
   
     basis = new int[numberOfConstraints];
     for (int i = 0; i < numberOfConstraints; i++)
      basis[i] = numberOfOriginalVariables + i;
   
     solve();
   
    }
   
    // run simplex algorithm starting from initial BFS
    private void solve() {
     while (true) {
   
      
      int q = 0;
      // find entering column q
      if (maximizeOrMinimize) {
       q = dantzig();
      } else {
       q = dantzigNegative();
      }
      if (q == -1)
       break; // optimal
   
      // find leaving row p
      int p = minRatioRule(q);
      if (p == -1)
       throw new ArithmeticException("Linear program is unbounded");
   
      pivot(p, q);
   
      
      basis[p] = q;
   
     }
    }
   
    // index of a non-basic column with most positive cost
    private int dantzig() {
     int q = 0;
     for (int j = 1; j < numberOfConstraints + numberOfOriginalVariables; j++)
      if (tableaux[numberOfConstraints][j] > tableaux[numberOfConstraints][q])
       q = j;
   
     if (tableaux[numberOfConstraints][q] <= 0)
      return -1; 
     else
      return q;
    }
   
    // index of a non-basic column with most negative cost
    private int dantzigNegative() {
     int q = 0;
     for (int j = 1; j < numberOfConstraints + numberOfOriginalVariables; j++)
      if (tableaux[numberOfConstraints][j] < tableaux[numberOfConstraints][q])
       q = j;
   
     if (tableaux[numberOfConstraints][q] >= 0)
      return -1; // optimal
     else
      return q;
    }
   
    // find row p using min ratio rule (-1 if no such row)
    private int minRatioRule(int q) {
     int p = -1;
     for (int i = 0; i < numberOfConstraints; i++) {
      if (tableaux[i][q] <= 0)
       continue;
      else if (p == -1)
       p = i;
      else if ((tableaux[i][numberOfConstraints
        + numberOfOriginalVariables] / tableaux[i][q]) < (tableaux[p][numberOfConstraints
        + numberOfOriginalVariables] / tableaux[p][q]))
       p = i;
     }
     return p;
    }
   
    
    private void pivot(int p, int q) {
   
     for (int i = 0; i <= numberOfConstraints; i++)
      for (int j = 0; j <= numberOfConstraints
        + numberOfOriginalVariables; j++)
       if (i != p && j != q)
        tableaux[i][j] -= tableaux[p][j] * tableaux[i][q]
          / tableaux[p][q];
   
     for (int i = 0; i <= numberOfConstraints; i++)
      if (i != p)
       tableaux[i][q] = 0.0;
   
     for (int j = 0; j <= numberOfConstraints + numberOfOriginalVariables; j++)
      if (j != q)
       tableaux[p][j] /= tableaux[p][q];
     tableaux[p][q] = 1.0;
    }
   
    public double value() {
     return -tableaux[numberOfConstraints][numberOfConstraints
       + numberOfOriginalVariables];
    }
   
    public double[] primal() {
     double[] x = new double[numberOfOriginalVariables];
     for (int i = 0; i < numberOfConstraints; i++)
      if (basis[i] < numberOfOriginalVariables)
       x[basis[i]] = tableaux[i][numberOfConstraints
         + numberOfOriginalVariables];
     return x;
    }
   
  
   
  
    public enum Constraint {
     lessThan, equal, greatherThan
    }
   
    public static class Modeler {
     private double[][] a; 
     private int numberOfConstraints; 
     private int numberOfOriginalVariables; 
   
     public Modeler(double[][] constraintLeftSide,
       double[] constraintRightSide, Constraint[] constraintOperator,
       double[] objectiveFunction) {
      numberOfConstraints = constraintRightSide.length;
      numberOfOriginalVariables = objectiveFunction.length;
      a = new double[numberOfConstraints + 1][numberOfOriginalVariables
        + numberOfConstraints + 1];
   
      for (int i = 0; i < numberOfConstraints; i++) {
       for (int j = 0; j < numberOfOriginalVariables; j++) {
        a[i][j] = constraintLeftSide[i][j];
       }
      }
   
      for (int i = 0; i < numberOfConstraints; i++)
       a[i][numberOfConstraints + numberOfOriginalVariables] = constraintRightSide[i];
   
      for (int i = 0; i < numberOfConstraints; i++) {
       int slack = 0;
       switch (constraintOperator[i]) {
       case greatherThan:
        slack = -1;
        break;
       case lessThan:
        slack = 1;
        break;
       default:
       }
       a[i][numberOfOriginalVariables + i] = slack;
      }
   
      for (int j = 0; j < numberOfOriginalVariables; j++)
       a[numberOfConstraints][j] = objectiveFunction[j];
     }
   
     public double[][] getTableaux() {
      return a;
     }
   
     public int getNumberOfConstraint() {
      return numberOfConstraints;
     }
   
     public int getNumberOfOriginalVariable() {
      return numberOfOriginalVariables;
     }
    }
}
