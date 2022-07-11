//package com.company;
//
///*
//Modify the previous HeavenlyBody example so that the HeavenlyBody
//class also has a "bodyType" field. This field will store the
//type of HeavenlyBody (such as STAR, PLANET, MOON, etc).
//
//You can include as many types as you want, but must support at
//least PLANET and MOON.
//
//For each of the types that you support, subclass the HeavenlyBody class
//so that your program can create objects of the appropriate type.
//
//Although astronomers may shudder at this, our solar systems will
//allow two bodies to have the same name as long as they are not the
//same type of body: so you could have a star called "BetaMinor" and
//an asteroid also called "BetaMinor", for example.
//
//Hint: This is much easier to implement for the Set than it is for the Map,
//because the Map will need a key that uses both fields.
//
//There is a restriction that the only satellites that planets can have must
//be moons. Even if you don't implement a STAR type, though, your program
//should not prevent one being added in the future (and a STAR's satellites
//can be almost every kind of HeavenlyBody).
//
//Test cases:
//1. The planets and moons that we added in the previous video should appear in
//the solarSystem collection and in the sets of moons for the appropriate planets.
//
//2. a.equals(b) must return the same result as b.equals(a) - equals is symmetric.
//
//3. Attempting to add a duplicate to a Set must result in no change to the set (so
//the original value is not replaced by the new one).
//
//4. Attempting to add a duplicate to a Map results in the original being replaced
//by the new object.
//
//5. Two bodies with the same name but different designations can be added to the same set.
//
//6. Two bodies with the same name but different designations can be added to the same map,
//and can be retrieved from the map.
//*/
//
package com.company;

import java.util.HashSet;
import java.util.Set;

public abstract class HeavenlyBody {
    private final Key key;
    private final double orbitalPeriod;
    private final Set<HeavenlyBody> satellites;

    public enum BodyTypes{
        STAR,
        PLANET,
        DWARF_PLANET,
        MOON,
        COMET,
        ASTEROID
    }

    public HeavenlyBody(String name, double orbitalPeriod, BodyTypes bodyType) {
        this.key = new Key(name, bodyType);
        this.orbitalPeriod = orbitalPeriod;
        this.satellites = new HashSet<>();
    }

    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public Key getKey() {
        return key;
    }

    public boolean addSatellite(HeavenlyBody moon){
        return this.satellites.add(moon);
    }

    public Set<HeavenlyBody> getSatellites() {
        return new HashSet<>(this.satellites);
    }

    @Override
    public final boolean equals(Object obj){
        if (this == obj){
            return true;
        }

        if (obj instanceof HeavenlyBody) {
            HeavenlyBody theObject = (HeavenlyBody) obj;
            return this.key.equals(theObject.getKey());
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return this.key.hashCode();
    }

    public static Key makeKey(String name, BodyTypes bodyType){
        return new Key(name, bodyType);
    }

    @Override
    public String toString() {
        return this.key.name + ": " + this.key.bodyType + ", " + this.orbitalPeriod;
    }

    public static final class Key{
        private String name;
        private BodyTypes bodyType;

        private Key(String name, BodyTypes bodyType) {
            this.name = name;
            this.bodyType = bodyType;
        }

        public String getName() {
            return name;
        }

        public BodyTypes getBodyType() {
            return bodyType;
        }

        @Override
        public int hashCode() {
            return this.name.hashCode() + 57 + this.bodyType.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            Key key = (Key) obj;
            if (this.name.equals(key.getName())){
                return (this.bodyType == key.getBodyType());
            }
            return false;
        }

        @Override
        public String toString() {
            return this.name + ": " + this.bodyType;
        }
    }
}
