package com.ecash.ecashcore.vo;

public class ReportContent
{
  private int create;
  private int lock;
  private int unLock;
  private int remove;

  public ReportContent()
  {
    this(0, 0, 0, 0);
  }

  public ReportContent(int create, int lock, int unLock, int remove)
  {
    super();
    this.create = create;
    this.lock = lock;
    this.unLock = unLock;
    this.remove = remove;
  }

  public int getCreate()
  {
    return create;
  }

  public void setCreate(int create)
  {
    this.create = create;
  }

  public int getLock()
  {
    return lock;
  }

  public void setLock(int lock)
  {
    this.lock = lock;
  }

  public int getUnLock()
  {
    return unLock;
  }

  public void setUnLock(int unLock)
  {
    this.unLock = unLock;
  }

  public int getRemove()
  {
    return remove;
  }

  public void setRemove(int remove)
  {
    this.remove = remove;
  }

}
