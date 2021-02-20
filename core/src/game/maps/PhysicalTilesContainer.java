package game.maps;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import game.actors.Actor;
import game.actors.ActorFactory;
import game.actors.Constants;
import game.ecs.factories.EntityFactory;
import game.system.Box2DSingleton;

public class PhysicalTilesContainer {

    private List<Body> staticBodies = new ArrayList<>();
    private List<String> physicalTileSetsNames = Arrays.asList("Brick");

    public PhysicalTilesContainer(TiledMap tiledMap) {
        super();
        HashMap<Integer, String> idToSetName = new HashMap<>();
        TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
        Iterator<TiledMapTileSet> tileSetsIterator = tiledMap.getTileSets().iterator();
        while (tileSetsIterator.hasNext()) {
            TiledMapTileSet current = tileSetsIterator.next();
            Iterator<TiledMapTile> tilesIterator = current.iterator();
            while (tilesIterator.hasNext()) {
                TiledMapTile tile = tilesIterator.next();
                idToSetName.put(tile.getId(), current.getName());
            }
        }

        for (int x = 0; x < layer.getWidth(); x ++) {
            for (int y = 0; y < layer.getHeight(); y ++) {
                TiledMapTile currentTile = layer.getCell(x, y).getTile();
                String tileSetName = idToSetName.get(currentTile.getId());
                if (physicalTileSetsNames.contains(tileSetName)) {

                    Vector2 tilePosition = new Vector2(x + 0.5f, y + 0.5f);
                    Vector2 tileWorldPosition = tilePosition; //.scl(mapFactor * Constants.WORLD_TO_BOX);//.scl(Constants.WORLD_TO_BOX * mapFactor);
                    EntityFactory.getInstance().createWallTile(tileWorldPosition, 1.0f, 1.0f);

                }
            }
        }
    }
}
