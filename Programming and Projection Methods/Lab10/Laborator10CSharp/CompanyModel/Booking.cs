/**
 * Autogenerated by Thrift Compiler (0.12.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;
using System.IO;
using Thrift;
using Thrift.Collections;
using System.Runtime.Serialization;
using CompanyMode;
using Thrift.Protocol;
using Thrift.Transport;

namespace CompanyModel
{

  #if !SILVERLIGHT
  [Serializable]
  #endif
  public partial class Booking : TBase,IHasID<KeyValuePair<Clientj,Ride>>
  {

    public Clientj Client { get; set; }

    public Ride Ride { get; set; }

    public int Nr_places_wanted { get; set; }

    public string Places { get; set; }

    public Booking() {
    }

    public Booking(Clientj client, Ride ride, int nr_places_wanted, string places) : this() {
      this.Client = client;
      this.Ride = ride;
      this.Nr_places_wanted = nr_places_wanted;
      this.Places = places;
    }

    public void Read (TProtocol iprot)
    {
      iprot.IncrementRecursionDepth();
      try
      {
        bool isset_client = false;
        bool isset_ride = false;
        bool isset_nr_places_wanted = false;
        bool isset_places = false;
        TField field;
        iprot.ReadStructBegin();
        while (true)
        {
          field = iprot.ReadFieldBegin();
          if (field.Type == TType.Stop) { 
            break;
          }
          switch (field.ID)
          {
            case 1:
              if (field.Type == TType.Struct) {
                Client = new Clientj();
                Client.Read(iprot);
                isset_client = true;
              } else { 
                TProtocolUtil.Skip(iprot, field.Type);
              }
              break;
            case 2:
              if (field.Type == TType.Struct) {
                Ride = new Ride();
                Ride.Read(iprot);
                isset_ride = true;
              } else { 
                TProtocolUtil.Skip(iprot, field.Type);
              }
              break;
            case 3:
              if (field.Type == TType.I32) {
                Nr_places_wanted = iprot.ReadI32();
                isset_nr_places_wanted = true;
              } else { 
                TProtocolUtil.Skip(iprot, field.Type);
              }
              break;
            case 4:
              if (field.Type == TType.String) {
                Places = iprot.ReadString();
                isset_places = true;
              } else { 
                TProtocolUtil.Skip(iprot, field.Type);
              }
              break;
            default: 
              TProtocolUtil.Skip(iprot, field.Type);
              break;
          }
          iprot.ReadFieldEnd();
        }
        iprot.ReadStructEnd();
        if (!isset_client)
          throw new TProtocolException(TProtocolException.INVALID_DATA, "required field Client not set");
        if (!isset_ride)
          throw new TProtocolException(TProtocolException.INVALID_DATA, "required field Ride not set");
        if (!isset_nr_places_wanted)
          throw new TProtocolException(TProtocolException.INVALID_DATA, "required field Nr_places_wanted not set");
        if (!isset_places)
          throw new TProtocolException(TProtocolException.INVALID_DATA, "required field Places not set");
      }
      finally
      {
        iprot.DecrementRecursionDepth();
      }
    }

    public void Write(TProtocol oprot) {
      oprot.IncrementRecursionDepth();
      try
      {
        TStruct struc = new TStruct("Booking");
        oprot.WriteStructBegin(struc);
        TField field = new TField();
        if (Client == null)
          throw new TProtocolException(TProtocolException.INVALID_DATA, "required field Client not set");
        field.Name = "client";
        field.Type = TType.Struct;
        field.ID = 1;
        oprot.WriteFieldBegin(field);
        Client.Write(oprot);
        oprot.WriteFieldEnd();
        if (Ride == null)
          throw new TProtocolException(TProtocolException.INVALID_DATA, "required field Ride not set");
        field.Name = "ride";
        field.Type = TType.Struct;
        field.ID = 2;
        oprot.WriteFieldBegin(field);
        Ride.Write(oprot);
        oprot.WriteFieldEnd();
        field.Name = "nr_places_wanted";
        field.Type = TType.I32;
        field.ID = 3;
        oprot.WriteFieldBegin(field);
        oprot.WriteI32(Nr_places_wanted);
        oprot.WriteFieldEnd();
        if (Places == null)
          throw new TProtocolException(TProtocolException.INVALID_DATA, "required field Places not set");
        field.Name = "places";
        field.Type = TType.String;
        field.ID = 4;
        oprot.WriteFieldBegin(field);
        oprot.WriteString(Places);
        oprot.WriteFieldEnd();
        oprot.WriteFieldStop();
        oprot.WriteStructEnd();
      }
      finally
      {
        oprot.DecrementRecursionDepth();
      }
    }

    public override string ToString() {
      StringBuilder __sb = new StringBuilder("Booking(");
      __sb.Append(", Client: ");
      __sb.Append(Client== null ? "<null>" : Client.ToString());
      __sb.Append(", Ride: ");
      __sb.Append(Ride== null ? "<null>" : Ride.ToString());
      __sb.Append(", Nr_places_wanted: ");
      __sb.Append(Nr_places_wanted);
      __sb.Append(", Places: ");
      __sb.Append(Places);
      __sb.Append(")");
      return __sb.ToString();
    }

    public KeyValuePair<Clientj, Ride> Id { get; set; }
  }

}
