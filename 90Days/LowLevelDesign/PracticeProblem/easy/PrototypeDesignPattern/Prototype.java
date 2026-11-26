package easy.PrototypeDesignPattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Implement interface Prototype<T> with T clone()
interface Prototype<Enemy>{
    Enemy clone();
}
// Provided classes. Complete only the clone() bodies marked TODO.
class Enemy implements Prototype<Enemy> {
    private final String type;
    private int health;
    private final int speed;
    private final List<String> loot;

    public Enemy(String type, int health, int speed, List<String> loot) {
        this.type = type;
        this.health = health;
        this.speed = speed;
        this.loot = loot;
    }

    public String getType() {
        return type;
    }

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

    public List<String> getLoot() {
        return loot;
    }

    public void addLoot(String item) {
        loot.add(item);
    }

    public void takeDamage(int amount) {
        health = Math.max(0, health - amount);
    }

    public String describe() {
        return type + " (hp " + health + ", speed " + speed + ") loot [" + String.join(", ", loot) + "]";
    }

    @Override
    public Enemy clone() {
        return new Enemy(
                this.type,
                this.health,
                this.speed,
                new ArrayList<>(this.loot)
        );
    }
}

// Provided judge-facing facade. Do not modify.
class EnemySpawner {
    private final Map<String, Enemy> prototypes = new HashMap<>();
    private final Map<String, Enemy> instances = new HashMap<>();

    public EnemySpawner() {
    }

    public boolean registerPrototype(String name, String type, int health, int speed) {
        if (name.isEmpty() || health <= 0 || prototypes.containsKey(name)) {
            return false;
        }
        prototypes.put(name, new Enemy(type, health, speed, new ArrayList<>()));
        return true;
    }

    public boolean addLoot(String name, String item) {
        Enemy prototype = prototypes.get(name);
        if (prototype == null) {
            return false;
        }
        prototype.addLoot(item);
        return true;
    }

    public boolean spawn(String name, String id) {
        Enemy prototype = prototypes.get(name);
        if (prototype == null || id.isEmpty() || instances.containsKey(id)) {
            return false;
        }
        Enemy copy = prototype.clone();
        if (copy == null) {
            return false;
        }
        instances.put(id, copy);
        return true;
    }

    public boolean damage(String id, int amount) {
        Enemy enemy = instances.get(id);
        if (enemy == null || amount <= 0) {
            return false;
        }
        enemy.takeDamage(amount);
        return true;
    }

    public boolean addInstanceLoot(String id, String item) {
        Enemy enemy = instances.get(id);
        if (enemy == null) {
            return false;
        }
        enemy.addLoot(item);
        return true;
    }

    public String describe(String id) {
        Enemy enemy = instances.get(id);
        return enemy == null ? "MISSING" : enemy.describe();
    }

    public String describePrototype(String name) {
        Enemy prototype = prototypes.get(name);
        return prototype == null ? "MISSING" : prototype.describe();
    }

    public int instanceCount() {
        return instances.size();
    }
}
