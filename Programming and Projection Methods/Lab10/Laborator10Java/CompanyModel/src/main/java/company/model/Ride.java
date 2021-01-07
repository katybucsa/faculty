/**
 * Autogenerated by Thrift Compiler (0.12.0)
 * <p>
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *
 * @generated
 */
package company.model;

import org.springframework.util.StringUtils;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.12.0)", date = "2019-04-30")
public class Ride implements org.apache.thrift.TBase<Ride, Ride._Fields>, java.io.Serializable, Cloneable, Comparable<Ride>, HasID<Integer> {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Ride");

    private static final org.apache.thrift.protocol.TField RIDE_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("ride_id", org.apache.thrift.protocol.TType.I32, (short) 1);
    private static final org.apache.thrift.protocol.TField DESTINATION_FIELD_DESC = new org.apache.thrift.protocol.TField("destination", org.apache.thrift.protocol.TType.STRING, (short) 2);
    private static final org.apache.thrift.protocol.TField DATE_FIELD_DESC = new org.apache.thrift.protocol.TField("date", org.apache.thrift.protocol.TType.STRING, (short) 3);
    private static final org.apache.thrift.protocol.TField HOUR_FIELD_DESC = new org.apache.thrift.protocol.TField("hour", org.apache.thrift.protocol.TType.STRING, (short) 4);
    private static final org.apache.thrift.protocol.TField PLACES_FIELD_DESC = new org.apache.thrift.protocol.TField("places", org.apache.thrift.protocol.TType.STRING, (short) 5);

    private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new RideStandardSchemeFactory();
    private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new RideTupleSchemeFactory();

    public int ride_id; // required
    public @org.apache.thrift.annotation.Nullable
    String destination; // required
    public @org.apache.thrift.annotation.Nullable
    String date; // required
    public @org.apache.thrift.annotation.Nullable
    String hour; // required
    public @org.apache.thrift.annotation.Nullable
    String places; // optional

    /**
     * The set of fields this struct contains, along with convenience methods for finding and manipulating them.
     */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
        RIDE_ID((short) 1, "ride_id"),
        DESTINATION((short) 2, "destination"),
        DATE((short) 3, "date"),
        HOUR((short) 4, "hour"),
        PLACES((short) 5, "places");

        private static final java.util.Map<String, _Fields> byName = new java.util.HashMap<String, _Fields>();

        static {
            for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
                byName.put(field.getFieldName(), field);
            }
        }

        /**
         * Find the _Fields constant that matches fieldId, or null if its not found.
         */
        @org.apache.thrift.annotation.Nullable
        public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
                case 1: // RIDE_ID
                    return RIDE_ID;
                case 2: // DESTINATION
                    return DESTINATION;
                case 3: // DATE
                    return DATE;
                case 4: // HOUR
                    return HOUR;
                case 5: // PLACES
                    return PLACES;
                default:
                    return null;
            }
        }

        /**
         * Find the _Fields constant that matches fieldId, throwing an exception
         * if it is not found.
         */
        public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            return fields;
        }

        /**
         * Find the _Fields constant that matches name, or null if its not found.
         */
        @org.apache.thrift.annotation.Nullable
        public static _Fields findByName(String name) {
            return byName.get(name);
        }

        private final short _thriftId;
        private final String _fieldName;

        _Fields(short thriftId, String fieldName) {
            _thriftId = thriftId;
            _fieldName = fieldName;
        }

        public short getThriftFieldId() {
            return _thriftId;
        }

        public String getFieldName() {
            return _fieldName;
        }
    }

    // isset id assignments
    private static final int __RIDE_ID_ISSET_ID = 0;
    private byte __isset_bitfield = 0;
    private static final _Fields optionals[] = {_Fields.PLACES};
    public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;

    static {
        java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
        tmpMap.put(_Fields.RIDE_ID, new org.apache.thrift.meta_data.FieldMetaData("ride_id", org.apache.thrift.TFieldRequirementType.REQUIRED,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32, "int")));
        tmpMap.put(_Fields.DESTINATION, new org.apache.thrift.meta_data.FieldMetaData("destination", org.apache.thrift.TFieldRequirementType.REQUIRED,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
        tmpMap.put(_Fields.DATE, new org.apache.thrift.meta_data.FieldMetaData("date", org.apache.thrift.TFieldRequirementType.REQUIRED,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
        tmpMap.put(_Fields.HOUR, new org.apache.thrift.meta_data.FieldMetaData("hour", org.apache.thrift.TFieldRequirementType.REQUIRED,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
        tmpMap.put(_Fields.PLACES, new org.apache.thrift.meta_data.FieldMetaData("places", org.apache.thrift.TFieldRequirementType.OPTIONAL,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
        metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
        org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Ride.class, metaDataMap);
    }

    public Ride() {
    }

    public Ride(
            int ride_id,
            String destination,
            String date,
            String hour) {
        this();
        this.ride_id = ride_id;
        setRide_idIsSet(true);
        this.destination = destination;
        this.date = date;
        this.hour = hour;
        this.places = "0000000000000000000";
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public Ride(Ride other) {
        __isset_bitfield = other.__isset_bitfield;
        this.ride_id = other.ride_id;
        if (other.isSetDestination()) {
            this.destination = other.destination;
        }
        if (other.isSetDate()) {
            this.date = other.date;
        }
        if (other.isSetHour()) {
            this.hour = other.hour;
        }
        if (other.isSetPlaces()) {
            this.places = other.places;
        }
    }

    public Ride deepCopy() {
        return new Ride(this);
    }

    @Override
    public void clear() {
        setRide_idIsSet(false);
        this.ride_id = 0;
        this.destination = null;
        this.date = null;
        this.hour = null;
        this.places = null;
    }

    public Integer getID() {
        return this.ride_id;
    }

    public void setID(Integer ride_id) {
        this.ride_id = ride_id;
        setRide_idIsSet(true);
    }

    public void unsetRide_id() {
        __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __RIDE_ID_ISSET_ID);
    }

    /**
     * Returns true if field ride_id is set (has been assigned a value) and false otherwise
     */
    public boolean isSetRide_id() {
        return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __RIDE_ID_ISSET_ID);
    }

    public void setRide_idIsSet(boolean value) {
        __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __RIDE_ID_ISSET_ID, value);
    }

    @org.apache.thrift.annotation.Nullable
    public String getDestination() {
        return this.destination;
    }

    public Ride setDestination(@org.apache.thrift.annotation.Nullable String destination) {
        this.destination = destination;
        return this;
    }

    public void unsetDestination() {
        this.destination = null;
    }

    /**
     * Returns true if field destination is set (has been assigned a value) and false otherwise
     */
    public boolean isSetDestination() {
        return this.destination != null;
    }

    public void setDestinationIsSet(boolean value) {
        if (!value) {
            this.destination = null;
        }
    }

    @org.apache.thrift.annotation.Nullable
    public String getDate() {
        return this.date;
    }

    public Ride setDate(@org.apache.thrift.annotation.Nullable String date) {
        this.date = date;
        return this;
    }

    public void unsetDate() {
        this.date = null;
    }

    /**
     * Returns true if field date is set (has been assigned a value) and false otherwise
     */
    public boolean isSetDate() {
        return this.date != null;
    }

    public void setDateIsSet(boolean value) {
        if (!value) {
            this.date = null;
        }
    }

    @org.apache.thrift.annotation.Nullable
    public String getHour() {
        return this.hour;
    }

    public Ride setHour(@org.apache.thrift.annotation.Nullable String hour) {
        this.hour = hour;
        return this;
    }

    public void unsetHour() {
        this.hour = null;
    }

    /**
     * Returns true if field hour is set (has been assigned a value) and false otherwise
     */
    public boolean isSetHour() {
        return this.hour != null;
    }

    public void setHourIsSet(boolean value) {
        if (!value) {
            this.hour = null;
        }
    }

    @org.apache.thrift.annotation.Nullable
    public String getPlaces() {
        return this.places;
    }

    public Ride setPlaces(@org.apache.thrift.annotation.Nullable String places) {
        this.places = places;
        return this;
    }


    public int getNrPlacesAvailable() {
        return StringUtils.countOccurrencesOf(places, "0") - 1;
    }


    public void unsetPlaces() {
        this.places = null;
    }

    /**
     * Returns true if field places is set (has been assigned a value) and false otherwise
     */
    public boolean isSetPlaces() {
        return this.places != null;
    }

    public void setPlacesIsSet(boolean value) {
        if (!value) {
            this.places = null;
        }
    }

    public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable Object value) {
        switch (field) {
            case RIDE_ID:
                if (value == null) {
                    unsetRide_id();
                } else {
                    setID((Integer) value);
                }
                break;

            case DESTINATION:
                if (value == null) {
                    unsetDestination();
                } else {
                    setDestination((String) value);
                }
                break;

            case DATE:
                if (value == null) {
                    unsetDate();
                } else {
                    setDate((String) value);
                }
                break;

            case HOUR:
                if (value == null) {
                    unsetHour();
                } else {
                    setHour((String) value);
                }
                break;

            case PLACES:
                if (value == null) {
                    unsetPlaces();
                } else {
                    setPlaces((String) value);
                }
                break;

        }
    }

    @org.apache.thrift.annotation.Nullable
    public Object getFieldValue(_Fields field) {
        switch (field) {
            case RIDE_ID:
                return getID();

            case DESTINATION:
                return getDestination();

            case DATE:
                return getDate();

            case HOUR:
                return getHour();

            case PLACES:
                return getPlaces();

        }
        throw new IllegalStateException();
    }

    /**
     * Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise
     */
    public boolean isSet(_Fields field) {
        if (field == null) {
            throw new IllegalArgumentException();
        }

        switch (field) {
            case RIDE_ID:
                return isSetRide_id();
            case DESTINATION:
                return isSetDestination();
            case DATE:
                return isSetDate();
            case HOUR:
                return isSetHour();
            case PLACES:
                return isSetPlaces();
        }
        throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
        if (that == null)
            return false;
        if (that instanceof Ride)
            return this.equals((Ride) that);
        return false;
    }

    public boolean equals(Ride that) {
        if (that == null)
            return false;
        if (this == that)
            return true;

        boolean this_present_ride_id = true;
        boolean that_present_ride_id = true;
        if (this_present_ride_id || that_present_ride_id) {
            if (!(this_present_ride_id && that_present_ride_id))
                return false;
            if (this.ride_id != that.ride_id)
                return false;
        }

        boolean this_present_destination = true && this.isSetDestination();
        boolean that_present_destination = true && that.isSetDestination();
        if (this_present_destination || that_present_destination) {
            if (!(this_present_destination && that_present_destination))
                return false;
            if (!this.destination.equals(that.destination))
                return false;
        }

        boolean this_present_date = true && this.isSetDate();
        boolean that_present_date = true && that.isSetDate();
        if (this_present_date || that_present_date) {
            if (!(this_present_date && that_present_date))
                return false;
            if (!this.date.equals(that.date))
                return false;
        }

        boolean this_present_hour = true && this.isSetHour();
        boolean that_present_hour = true && that.isSetHour();
        if (this_present_hour || that_present_hour) {
            if (!(this_present_hour && that_present_hour))
                return false;
            if (!this.hour.equals(that.hour))
                return false;
        }

        boolean this_present_places = true && this.isSetPlaces();
        boolean that_present_places = true && that.isSetPlaces();
        if (this_present_places || that_present_places) {
            if (!(this_present_places && that_present_places))
                return false;
            if (!this.places.equals(that.places))
                return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;

        hashCode = hashCode * 8191 + ride_id;

        hashCode = hashCode * 8191 + ((isSetDestination()) ? 131071 : 524287);
        if (isSetDestination())
            hashCode = hashCode * 8191 + destination.hashCode();

        hashCode = hashCode * 8191 + ((isSetDate()) ? 131071 : 524287);
        if (isSetDate())
            hashCode = hashCode * 8191 + date.hashCode();

        hashCode = hashCode * 8191 + ((isSetHour()) ? 131071 : 524287);
        if (isSetHour())
            hashCode = hashCode * 8191 + hour.hashCode();

        hashCode = hashCode * 8191 + ((isSetPlaces()) ? 131071 : 524287);
        if (isSetPlaces())
            hashCode = hashCode * 8191 + places.hashCode();

        return hashCode;
    }

    @Override
    public int compareTo(Ride other) {
        if (!getClass().equals(other.getClass())) {
            return getClass().getName().compareTo(other.getClass().getName());
        }

        int lastComparison = 0;

        lastComparison = Boolean.valueOf(isSetRide_id()).compareTo(other.isSetRide_id());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetRide_id()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ride_id, other.ride_id);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetDestination()).compareTo(other.isSetDestination());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetDestination()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.destination, other.destination);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetDate()).compareTo(other.isSetDate());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetDate()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.date, other.date);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetHour()).compareTo(other.isSetHour());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetHour()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.hour, other.hour);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetPlaces()).compareTo(other.isSetPlaces());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetPlaces()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.places, other.places);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        return 0;
    }

    @org.apache.thrift.annotation.Nullable
    public _Fields fieldForId(int fieldId) {
        return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
        scheme(iprot).read(iprot, this);
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
        scheme(oprot).write(oprot, this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Ride(");
        boolean first = true;

        sb.append("ride_id:");
        sb.append(this.ride_id);
        first = false;
        if (!first) sb.append(", ");
        sb.append("destination:");
        if (this.destination == null) {
            sb.append("null");
        } else {
            sb.append(this.destination);
        }
        first = false;
        if (!first) sb.append(", ");
        sb.append("date:");
        if (this.date == null) {
            sb.append("null");
        } else {
            sb.append(this.date);
        }
        first = false;
        if (!first) sb.append(", ");
        sb.append("hour:");
        if (this.hour == null) {
            sb.append("null");
        } else {
            sb.append(this.hour);
        }
        first = false;
        if (isSetPlaces()) {
            if (!first) sb.append(", ");
            sb.append("places:");
            if (this.places == null) {
                sb.append("null");
            } else {
                sb.append(this.places);
            }
            first = false;
        }
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
        // check for required fields
        // alas, we cannot check 'ride_id' because it's a primitive and you chose the non-beans generator.
        if (destination == null) {
            throw new org.apache.thrift.protocol.TProtocolException("Required field 'destination' was not present! Struct: " + toString());
        }
        if (date == null) {
            throw new org.apache.thrift.protocol.TProtocolException("Required field 'date' was not present! Struct: " + toString());
        }
        if (hour == null) {
            throw new org.apache.thrift.protocol.TProtocolException("Required field 'hour' was not present! Struct: " + toString());
        }
        // check for sub-struct validity
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
        try {
            write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
        } catch (org.apache.thrift.TException te) {
            throw new java.io.IOException(te);
        }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        try {
            // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
            __isset_bitfield = 0;
            read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
        } catch (org.apache.thrift.TException te) {
            throw new java.io.IOException(te);
        }
    }

    private static class RideStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
        public RideStandardScheme getScheme() {
            return new RideStandardScheme();
        }
    }

    private static class RideStandardScheme extends org.apache.thrift.scheme.StandardScheme<Ride> {

        public void read(org.apache.thrift.protocol.TProtocol iprot, Ride struct) throws org.apache.thrift.TException {
            org.apache.thrift.protocol.TField schemeField;
            iprot.readStructBegin();
            while (true) {
                schemeField = iprot.readFieldBegin();
                if (schemeField.type == org.apache.thrift.protocol.TType.STOP) {
                    break;
                }
                switch (schemeField.id) {
                    case 1: // RIDE_ID
                        if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
                            struct.ride_id = iprot.readI32();
                            struct.setRide_idIsSet(true);
                        } else {
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                        }
                        break;
                    case 2: // DESTINATION
                        if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                            struct.destination = iprot.readString();
                            struct.setDestinationIsSet(true);
                        } else {
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                        }
                        break;
                    case 3: // DATE
                        if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                            struct.date = iprot.readString();
                            struct.setDateIsSet(true);
                        } else {
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                        }
                        break;
                    case 4: // HOUR
                        if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                            struct.hour = iprot.readString();
                            struct.setHourIsSet(true);
                        } else {
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                        }
                        break;
                    case 5: // PLACES
                        if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                            struct.places = iprot.readString();
                            struct.setPlacesIsSet(true);
                        } else {
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                        }
                        break;
                    default:
                        org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                }
                iprot.readFieldEnd();
            }
            iprot.readStructEnd();

            // check for required fields of primitive type, which can't be checked in the validate method
            if (!struct.isSetRide_id()) {
                throw new org.apache.thrift.protocol.TProtocolException("Required field 'ride_id' was not found in serialized data! Struct: " + toString());
            }
            struct.validate();
        }

        public void write(org.apache.thrift.protocol.TProtocol oprot, Ride struct) throws org.apache.thrift.TException {
            struct.validate();

            oprot.writeStructBegin(STRUCT_DESC);
            oprot.writeFieldBegin(RIDE_ID_FIELD_DESC);
            oprot.writeI32(struct.ride_id);
            oprot.writeFieldEnd();
            if (struct.destination != null) {
                oprot.writeFieldBegin(DESTINATION_FIELD_DESC);
                oprot.writeString(struct.destination);
                oprot.writeFieldEnd();
            }
            if (struct.date != null) {
                oprot.writeFieldBegin(DATE_FIELD_DESC);
                oprot.writeString(struct.date);
                oprot.writeFieldEnd();
            }
            if (struct.hour != null) {
                oprot.writeFieldBegin(HOUR_FIELD_DESC);
                oprot.writeString(struct.hour);
                oprot.writeFieldEnd();
            }
            if (struct.places != null) {
                if (struct.isSetPlaces()) {
                    oprot.writeFieldBegin(PLACES_FIELD_DESC);
                    oprot.writeString(struct.places);
                    oprot.writeFieldEnd();
                }
            }
            oprot.writeFieldStop();
            oprot.writeStructEnd();
        }

    }

    private static class RideTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
        public RideTupleScheme getScheme() {
            return new RideTupleScheme();
        }
    }

    private static class RideTupleScheme extends org.apache.thrift.scheme.TupleScheme<Ride> {

        @Override
        public void write(org.apache.thrift.protocol.TProtocol prot, Ride struct) throws org.apache.thrift.TException {
            org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
            oprot.writeI32(struct.ride_id);
            oprot.writeString(struct.destination);
            oprot.writeString(struct.date);
            oprot.writeString(struct.hour);
            java.util.BitSet optionals = new java.util.BitSet();
            if (struct.isSetPlaces()) {
                optionals.set(0);
            }
            oprot.writeBitSet(optionals, 1);
            if (struct.isSetPlaces()) {
                oprot.writeString(struct.places);
            }
        }

        @Override
        public void read(org.apache.thrift.protocol.TProtocol prot, Ride struct) throws org.apache.thrift.TException {
            org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
            struct.ride_id = iprot.readI32();
            struct.setRide_idIsSet(true);
            struct.destination = iprot.readString();
            struct.setDestinationIsSet(true);
            struct.date = iprot.readString();
            struct.setDateIsSet(true);
            struct.hour = iprot.readString();
            struct.setHourIsSet(true);
            java.util.BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
                struct.places = iprot.readString();
                struct.setPlacesIsSet(true);
            }
        }
    }

    private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
        return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
    }
}

