package aed.neodatis;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

public class Main {

	public static void main(String[] args) {
		ODB odb = ODBFactory.open("d:\\neodatis");

		// Creo los jugadores
		Jugadores j1 = new Jugadores("María", "voleibol", "Madrid", 14);
		Jugadores j2 = new Jugadores("Miguel", "tenis", "Madrid", 15);
		Jugadores j3 = new Jugadores("Mario", "baloncesto", "Guadalajara", 15);
		Jugadores j4 = new Jugadores("Alicia", "tenis", "Madrid", 14);

		// Inserto los objetos
		odb.store(j1);
		odb.store(j2);
		odb.store(j3);
		odb.store(j4);

		// Mostrar datos
		Objects<Jugadores> objects = odb.getObjects(Jugadores.class);

		// Imprimo cuantos objetos me he traido de la BD
		System.out.println(objects.size() + " jugadores:");

		int i = 1;
		// Mientras haya objetos, los capturo y muestro
		while (objects.hasNext()) {
			Jugadores jug = objects.next();

			System.out.println((i++) + ", " + jug.getNombre() + ", " + jug.getDeporte() + ", " + jug.getCiudad() + ", "
					+ jug.getEdad());
		}
		
		// consultas
		IQuery query = new CriteriaQuery(Jugadores.class, Where.equal("deporte", "tenis"));
		query.orderByDesc("nombre,edad");
		Objects<Jugadores> objects2 = odb.getObjects(query);
		System.out.println();
		System.out.println(objects2.size() + " jugadores");
		
		ICriterion criterio = Where.equal("edad",14);
		CriteriaQuery query2 = new CriteriaQuery(Jugadores.class, criterio);
		Objects<Jugadores> objects3 = odb.getObjects(query2);
		System.out.println();
		System.out.println(objects3.size() + " jugadores");
		
		// Jugadores que empiezan por M
		ICriterion criterio2 = Where.like("nombre","M%");

		// Jugadores mayores de 14
		// Where.ge --> mayor o igual, Where.lt --> menor que, Where.le ---> menor o igual
		            // Where.Not --> Distinto
		            //Where.isNull("atributo") si un atributo es nulo
		            //Where.isNotNull("atributo") si un atributo es nulo
		ICriterion criterio3 = Where.gt("edad",14);
		
		// Suma de edades
		Values val = odb.getValues(new ValuesCriteriaQuery(Jugadores.class).sum("edad"));
		ObjectValues ov= val.nextValues();
		BigDecimal value = (BigDecimal)ov.getByAlias("edad");
		
		// Cuenta de jugadores
		Values val2 = odb.getValues(new ValuesCriteriaQuery(Jugadores.class).count("nombre"));
		ObjectValues ov2= val2.nextValues();
		BigInteger value2 = (BigInteger)ov2.getByAlias("nombre");
		
		
		// Edad máxima y mínima
		Values val4 = odb.getValues(new ValuesCriteriaQuery(Jugadores.class).min("edad").count("nombre").max("edad"));
		ObjectValues ov4= val4.nextValues();
		BigDecimal edadMin = (BigDecimal) ov4.getByIndex(0);
		BigInteger cantidad = (BigInteger) ov4.getByIndex(1);
		BigDecimal edadMax = (BigDecimal) ov4.getByIndex(2);
		
		
		/*// Hacemos la consulta para borrar a María
		IQuery borrar = new CriteriaQuery(Jugadores.class, Where.equal("nombre", "María"));
		// Cargamos los objetos que coincidan con esa consulta
		Objects<Jugadores> borrados = odb.getObjects(borrar);
		// Nos posicionamos en el primer resultado
		Jugadores jug=(Jugadores) odb.getObjects(borrar).getFirst();
		// Y lo borramos
		odb.delete(jug);*/
		
		//Modificar
		IQuery modificar = new CriteriaQuery(Jugadores.class, Where.equal("nombre", "María"));
		// A María le ponemos Tiro de barra aragonesa como deporte
		Objects<Jugadores> modificaciones = odb.getObjects(modificar);
		Jugadores maria=(Jugadores) odb.getObjects(modificar).getFirst();
		maria.setDeporte("Futbol");
		odb.store(maria);
		
		odb.close();
	}

}
