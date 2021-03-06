package ru.smolina.repositories.jdbc;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ru.smolina.domains.Ingredient;
import ru.smolina.domains.Taco;

@Repository
public class JdbcTacoRepository implements TacoRepository{
	
	private JdbcTemplate jdbc;
	
	@Autowired
	public JdbcTacoRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public Taco save(Taco taco) {
		long tacoId = saveTacoInfo(taco);
		taco.setId(tacoId);
		for (Ingredient ingredient : taco.getIngredients())
			saveIngredientToTaco(ingredient, tacoId);
		return taco;
	}
	
	private long saveTacoInfo(Taco taco) {
		taco.setCreatedat(new Date());		
		
		PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(
				"insert into Taco (name, createdAt) values (?,?)",
				Types.VARCHAR, Types.TIMESTAMP);
		pscFactory.setReturnGeneratedKeys(true);
		
		 PreparedStatementCreator psc = pscFactory.newPreparedStatementCreator(
						Arrays.asList(taco.getName(), new Timestamp(taco.getCreatedat().getTime())));
				
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(psc, keyHolder);
		
		Number n = keyHolder.getKey();
		
		return keyHolder.getKey().longValue();				
	}
	
	private void saveIngredientToTaco(Ingredient ingredient, long tacoId) {
		jdbc.update(
				"insert into Taco_ingredients (taco, ingredient) values (?,?)",
				tacoId, ingredient.getId());
	}

}
