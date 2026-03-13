
package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.Date;
import java.util.Scanner;

public class ClientDemo
{
    public static void main(String[] args)
    {
        Configuration cfg = new Configuration();
        cfg.configure();
        cfg.addAnnotatedClass(Department.class);

        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();

        Scanner sc = new Scanner(System.in);

        // INSERT
        session.beginTransaction();

        Department d = new Department();
        System.out.println("Enter Department Name:");
        d.setName(sc.nextLine());

        System.out.println("Enter Description:");
        d.setDescription(sc.nextLine());

        d.setDate(new Date());

        System.out.println("Enter Status:");
        d.setStatus(sc.nextLine());

        session.save(d);
        session.getTransaction().commit();

        System.out.println("Record Inserted Successfully");

        // DELETE
        session.beginTransaction();

        System.out.println("Enter Department ID to Delete:");
        int id = sc.nextInt();

        Department d1 = session.get(Department.class,id);

        if(d1!=null)
        {
            session.delete(d1);
            System.out.println("Record Deleted Successfully");
        }
        else
        {
            System.out.println("Record Not Found");
        }

        session.getTransaction().commit();

        session.close();
        factory.close();
    }
}
