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
using CompanyModel;
using Thrift.Protocol;
using Thrift.Transport;

namespace CompanyModel

{

  #if !SILVERLIGHT
  [Serializable]
  #endif
  public partial class Clientj : TBase,IHasID<int>
  {

    public int Id { get; set; }

    public string Name { get; set; }

    public Clientj() {
    }

    public Clientj(int id, string name) : this() {
      this.Id = id;
      this.Name = name;
    }

    public void Read (TProtocol iprot)
    {
      iprot.IncrementRecursionDepth();
      try
      {
        bool isset_client_id = false;
        bool isset_name = false;
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
              if (field.Type == TType.I32) {
                Id = iprot.ReadI32();
                isset_client_id = true;
              } else { 
                TProtocolUtil.Skip(iprot, field.Type);
              }
              break;
            case 2:
              if (field.Type == TType.String) {
                Name = iprot.ReadString();
                isset_name = true;
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
        if (!isset_client_id)
          throw new TProtocolException(TProtocolException.INVALID_DATA, "required field Client_id not set");
        if (!isset_name)
          throw new TProtocolException(TProtocolException.INVALID_DATA, "required field Name not set");
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
        TStruct struc = new TStruct("Clientj");
        oprot.WriteStructBegin(struc);
        TField field = new TField();
        field.Name = "client_id";
        field.Type = TType.I32;
        field.ID = 1;
        oprot.WriteFieldBegin(field);
        oprot.WriteI32(Id);
        oprot.WriteFieldEnd();
        if (Name == null)
          throw new TProtocolException(TProtocolException.INVALID_DATA, "required field Name not set");
        field.Name = "name";
        field.Type = TType.String;
        field.ID = 2;
        oprot.WriteFieldBegin(field);
        oprot.WriteString(Name);
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
      StringBuilder __sb = new StringBuilder("Clientj(");
      __sb.Append(", Client_id: ");
      __sb.Append(Id);
      __sb.Append(", Name: ");
      __sb.Append(Name);
      __sb.Append(")");
      return __sb.ToString();
    }

  }

}
