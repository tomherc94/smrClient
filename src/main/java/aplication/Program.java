package aplication;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.hyperic.sigar.shell.ShellCommandExecException;
import org.hyperic.sigar.shell.ShellCommandUsageException;

public class Program {

	public static void main(String[] args) throws ShellCommandUsageException, ShellCommandExecException{		
		
		SysInfo sys = new SysInfo();
		sys.processCommand(args);
		
		
		
		try {
		
		Socket cliente = new Socket(InetAddress.getLocalHost().getHostAddress(), 12345);
        System.out.println("O cliente se conectou ao servidor!");

        Scanner teclado = new Scanner(System.in);
        PrintStream saida = new PrintStream(cliente.getOutputStream());

        while (teclado.hasNextLine()) {
            saida.println(teclado.nextLine());
        }

        saida.close();
        teclado.close();
        cliente.close();
        
        
		}catch(UnknownHostException e) {
			System.out.println(e.getMessage());
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
