package aed.neodatis;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

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
		
		odb.close();
	}

}
