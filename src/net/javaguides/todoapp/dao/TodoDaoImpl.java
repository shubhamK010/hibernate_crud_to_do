package net.javaguides.todoapp.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import net.javaguides.todoapp.model.Todo;
import net.javaguides.todoapp.util.HibernateUtil;

/**
 * This DAO class provides CRUD database operations for the table todos in the
 * database.
 * 
 * 
 *
 */

public class TodoDaoImpl implements TodoDao {

	public TodoDaoImpl() {
	}

	@Override
	public void insertTodo(Todo todo) throws SQLException {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.save(todo);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	@Override
	public Todo selectTodo(long todoId) {
		Transaction transaction = null;
		Todo todo = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			todo = session.get(Todo.class, todoId);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return todo;
	}

	@Override
	public List<Todo> selectAllTodos() {

		Transaction transaction = null;
		List<Todo> todos = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			
			todos = session.createQuery("from Todo").getResultList();
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return todos;
	}

	@Override
	public void deleteTodo(long todoId) {
		
		
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Todo todo = session.get(Todo.class, todoId);
            if (todo != null) {
                session.delete(todo);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            // Handle the exception
            throw e;
        }
        
        
		/*
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
		    transaction = session.beginTransaction();

		    // Perform your database operations here

		    transaction.commit();
		} catch (Exception e) {
		    if (transaction != null) {
		        transaction.rollback();
		    }
		    e.printStackTrace();
		} finally {
		    if (session != null) {
		        session.close();
		    }
		}
		*/
        
    }

	@Override
	public void updateTodo(Todo todo) throws SQLException {
		
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.saveOrUpdate(todo);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
}
