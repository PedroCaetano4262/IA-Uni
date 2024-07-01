import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;

import static org.junit.Assert.*;


public class TripleUnitTests {
    //Testes ao Construtor
    @Test
    public void testConstructor() {
        Triple b = new Triple(0);
        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter ( writer ) ;
        pw.print(0);
        assertEquals(b.toString(), writer.toString());
        pw.close();
    }

    @Test
    public void testConstructor2() {
        Triple b = new Triple(12345);
        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter ( writer ) ;
        pw.print(12345);
        assertEquals(b.toString(), writer.toString());
        pw.close();
    }

    @Test
    public void testConstructor3() {
        Triple b = new Triple(-12345);
        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter ( writer ) ;
        pw.print(-12345);
        assertEquals(b.toString(), writer.toString());
        pw.close();
    }

    //Testes para Positivos A*
    @Test
    public void testPositivoAStar() {
        int value = 12;
        A s = new A();
        Iterator<A.State> it = s.solve(new Triple(value), new Triple(value*3));
        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter ( writer );
        StringWriter writer2 = new StringWriter();
        PrintWriter pw2 = new PrintWriter ( writer2 );
        for(int i = value; i <= (value*3)/2; i++){
            pw2.println(i);
        }
        pw2.println(value*3);
        pw2.print("\n");
        pw2.print(9);
        while(it.hasNext()) {
            A.State i = it.next();
            pw.println(i);
            if (!it.hasNext()) {
                pw.print("\n" + (int) i.getG());
                break;
            }

        }
        assertEquals(writer2.toString(), writer.toString());
        pw.close();
        pw2.close();
    }

    @Test
    public void testPositivoAStar2() {
        int value = 333;
        A s = new A();
        Iterator<A.State> it = s.solve(new Triple(value), new Triple(value*3));
        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter ( writer );
        StringWriter writer2 = new StringWriter();
        PrintWriter pw2 = new PrintWriter ( writer2 );
        for(int i = value; i <= (value*3)/2; i++){
            pw2.println(i);
        }
        pw2.println(998);
        pw2.println(value*3);
        pw2.print("\n");
        pw2.print(170);
        while(it.hasNext()) {
            A.State i = it.next();
            pw.println(i);
            if (!it.hasNext()) {
                pw.print("\n" + (int) i.getG());
                break;
            }

        }
        assertEquals(writer2.toString(), writer.toString());
        pw.close();
        pw2.close();
    }

    @Test
    public void testPositivoAStar3() {
        int value = 1000;
        A s = new A();
        Iterator<A.State> it = s.solve(new Triple(value), new Triple(value*3));
        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter ( writer );
        StringWriter writer2 = new StringWriter();
        PrintWriter pw2 = new PrintWriter ( writer2 );
        for(int i = value; i <= (value*3)/2; i++){
            pw2.println(i);
        }
        pw2.println(value*3);
        pw2.print("\n");
        pw2.print(503);
        while(it.hasNext()) {
            A.State i = it.next();
            pw.println(i);
            if (!it.hasNext()) {
                pw.print("\n" + (int) i.getG());
                break;
            }

        }
        assertEquals(writer2.toString(), writer.toString());
        pw.close();
        pw2.close();
    }


    //Testes para Negativos A*
    @Test
    public void testNegativoAStar() {
        int value = -4;
        A s = new A();
        Iterator<A.State> it = s.solve(new Triple(value), new Triple(value*3));
        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter ( writer );
        StringWriter writer2 = new StringWriter();
        PrintWriter pw2 = new PrintWriter ( writer2 );
        pw2.println(-4);
        pw2.println(-5);
        pw2.println(-6);
        pw2.println(-12);
        pw2.print("\n");
        pw2.print(7);
        while(it.hasNext()) {
            A.State i = it.next();
            pw.println(i);
            if (!it.hasNext()) {
                pw.print("\n" + (int) i.getG());
                break;
            }

        }
        assertEquals(writer2.toString(), writer.toString());
        pw.close();
        pw2.close();
    }

    @Test
    public void testNegativoAStar2() {
        int value = -133;
        A s = new A();
        Iterator<A.State> it = s.solve(new Triple(value), new Triple(value*3));
        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter ( writer );
        StringWriter writer2 = new StringWriter();
        PrintWriter pw2 = new PrintWriter ( writer2 );
        for(int i = value; i < (value*3)/4; i++){

            pw2.println(i);
        }
        pw2.println(-200);
        pw2.println(-400);
        pw2.println(-399);
        pw2.print("\n");
        pw2.print(40);
        while(it.hasNext()) {
            A.State i = it.next();
            pw.println(i);
            if (!it.hasNext()) {
                pw.print("\n" + (int) i.getG());
                break;
            }

        }
        assertEquals(writer2.toString(), writer.toString());
        pw.close();
        pw2.close();
    }
    @Test
    public void testNegativoAStar3() {
        int value = -2000;
        A s = new A();
        Iterator<A.State> it = s.solve(new Triple(value), new Triple(value*3));
        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter ( writer );
        StringWriter writer2 = new StringWriter();
        PrintWriter pw2 = new PrintWriter ( writer2 );
        for(int i = value; i <= (value*3)/4; i++){

            pw2.println(i);
        }
        pw2.println(-3000);
        pw2.println(-6000);
        pw2.print("\n");
        pw2.print(506);
        while(it.hasNext()) {
            A.State i = it.next();
            pw.println(i);
            if (!it.hasNext()) {
                pw.print("\n" + (int) i.getG());
                break;
            }

        }
        assertEquals(writer2.toString(), writer.toString());
        pw.close();
        pw2.close();
    }

    @Test
    public void testPositivoIDAStar() {
        int value = 10;
        IDA s = new IDA();
        Iterator<IDA.State> it = s.solve(new Triple(value), new Triple(value * 3));
        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        StringWriter writer2 = new StringWriter();
        PrintWriter pw2 = new PrintWriter(writer2);
        pw2.println(10);
        pw2.println(11);
        pw2.println(12);
        pw2.println(13);
        pw2.println(14);
        pw2.println(15);
        pw2.println(30);
        pw2.print("\n");
        pw2.print(8);
        while (it.hasNext()) {
            IDA.State i = it.next();
            pw.println(i);
            if (!it.hasNext()) {
                pw.print("\n" + (int) i.getG());
                break;
            }

        }
        assertEquals(writer2.toString(), writer.toString());
        pw.close();
        pw2.close();
    }

    @Test
    public void testPositivoIDAStar2() {
        int value = 333;
        IDA s = new IDA();
        Iterator<IDA.State> it = s.solve(new Triple(value), new Triple(value*3));
        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter ( writer );
        StringWriter writer2 = new StringWriter();
        PrintWriter pw2 = new PrintWriter ( writer2 );
        for(int i = value; i <= ((value*3)/2); i++){
            pw2.println(i);
        }
        pw2.println(998);
        pw2.println(999);
        pw2.print("\n");
        pw2.print(170);
        while(it.hasNext()) {
            IDA.State i = it.next();
            pw.println(i);
            if (!it.hasNext()) {
                pw.print("\n" + (int) i.getG());
                break;
            }

        }
        assertEquals(writer2.toString(), writer.toString());
        pw.close();
        pw2.close();
    }

    @Test
    public void testPositivoIDAStar3() {
        int value = 1000;
        IDA s = new IDA();
        Iterator<IDA.State> it = s.solve(new Triple(value), new Triple(value*3));
        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter ( writer );
        StringWriter writer2 = new StringWriter();
        PrintWriter pw2 = new PrintWriter ( writer2 );
        for(int i = value; i <= (value*3)/2; i++){
            pw2.println(i);
        }
        pw2.println(3000);
        pw2.print("\n");
        pw2.print(503);
        while(it.hasNext()) {
            IDA.State i = it.next();
            pw.println(i);
            if (!it.hasNext()) {
                pw.print("\n" + (int) i.getG());
                break;
            }

        }
        assertEquals(writer2.toString(), writer.toString());
        pw.close();
        pw2.close();
    }


    @Test
    public void testNegativoIDAStar1() {
        int value = -20;
        IDA s = new IDA();
        Iterator<IDA.State> it = s.solve(new Triple(value), new Triple(value * 3));
        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        StringWriter writer2 = new StringWriter();
        PrintWriter pw2 = new PrintWriter(writer2);
        pw2.println(-20);
        pw2.println(-19);
        pw2.println(-18);
        pw2.println(-17);
        pw2.println(-16);
        pw2.println(-15);
        pw2.println(-30);
        pw2.println(-60);
        pw2.print("\n");
        pw2.print(11);
        while (it.hasNext()) {
            IDA.State i = it.next();
            pw.println(i);
            if (!it.hasNext()) {
                pw.print("\n" + (int) i.getG());
                break;
            }

        }
        assertEquals(writer2.toString(), writer.toString());
        pw.close();
        pw2.close();
    }

    @Test
    public void testNegativoIDAStar2() {
        int value = -2000;
        IDA s = new IDA();
        Iterator<IDA.State> it = s.solve(new Triple(value), new Triple(value*3));
        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter ( writer );
        StringWriter writer2 = new StringWriter();
        PrintWriter pw2 = new PrintWriter ( writer2 );
        for(int i = value; i <= (value*3)/4; i++){

            pw2.println(i);
        }
        pw2.println(-3000);
        pw2.println(-6000);
        pw2.print("\n");
        pw2.print(506);
        while(it.hasNext()) {
            IDA.State i = it.next();
            pw.println(i);
            if (!it.hasNext()) {
                pw.print("\n" + (int) i.getG());
                break;
            }

        }
        assertEquals(writer2.toString(), writer.toString());
        pw.close();
        pw2.close();
    }

    @Test
    public void testNegativoIDAStar3() {
        int value = -1;
        IDA s = new IDA();
        Iterator<IDA.State> it = s.solve(new Triple(value), new Triple(value * 3));
        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        StringWriter writer2 = new StringWriter();
        PrintWriter pw2 = new PrintWriter(writer2);
        pw2.println(-1);
        pw2.println(-2);
        pw2.println(-3);
        pw2.print("\n");
        pw2.print(4);
        while (it.hasNext()) {
            IDA.State i = it.next();
            pw.println(i);
            if (!it.hasNext()) {
                pw.print("\n" + (int) i.getG());
                break;
            }

        }
        assertEquals(writer2.toString(), writer.toString());
        pw.close();
        pw2.close();
    }
}