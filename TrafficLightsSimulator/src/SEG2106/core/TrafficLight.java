package SEG2106.core;

/**
 * Modified TrafficLight class with three traffic modes: Low, Moderate, and High
 * Generated from UMPLE and modified for integration with TrafficLightManager
 * 
 * @author Aum Panchal (300415468)
 */
public class TrafficLight implements EventHandler
{
  //------------------------
  // MEMBER VARIABLES
  //------------------------

  // TrafficLight State Machines with three modes
  enum Status { 
    lowNorthSouthArrow, lowNorthSouthGreen, lowNorthSouthYellow, 
    lowNorthSouthRed, lowWestEastYellow,

    modNorthArrowGreen, modNorthYellow, modSouthArrowGreen, 
    modSouthYellow, modWestEastGreen, modWestEastYellow,

    highNorthArrowGreen, highNorthYellow, highSouthArrowGreen, 
    highSouthYellow, highWestArrowGreen, highWestEastGreen, 
    highWestEastYellow
  }
  
  private Status status;
  private TrafficLightManager trafficLightManager;

  //------------------------
  // CONSTRUCTOR
  //------------------------
  
  public TrafficLight(TrafficLightManager trafficLightManager)
  {
    this.trafficLightManager = trafficLightManager;
    setStatus(Status.lowNorthSouthArrow); 
    trafficLightManager.addEventHandler(this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getStatusFullName()
  {
    return status.toString();
  }

  public Status getStatus()
  {
    return status;
  }

  //------------------------
  // EVENT HANDLERS
  //------------------------

  @Override
  public boolean timerGreen()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case lowNorthSouthArrow:
        setStatus(Status.lowNorthSouthGreen);
        wasEventProcessed = true;
        break;
      case lowNorthSouthGreen:
        setStatus(Status.lowNorthSouthYellow);
        wasEventProcessed = true;
        break;
      case lowNorthSouthRed:
        setStatus(Status.lowWestEastYellow);
        wasEventProcessed = true;
        break;

      case modNorthArrowGreen:
        setStatus(Status.modNorthYellow);
        wasEventProcessed = true;
        break;
      case modSouthArrowGreen:
        setStatus(Status.modSouthYellow);
        wasEventProcessed = true;
        break;
      case modWestEastGreen:
        setStatus(Status.modWestEastYellow);
        wasEventProcessed = true;
        break;

      case highNorthArrowGreen:
        setStatus(Status.highNorthYellow);
        wasEventProcessed = true;
        break;
      case highSouthArrowGreen:
        setStatus(Status.highSouthYellow);
        wasEventProcessed = true;
        break;
      case highWestArrowGreen:
        setStatus(Status.highWestEastGreen);
        wasEventProcessed = true;
        break;
      case highWestEastGreen:
        setStatus(Status.highWestEastYellow);
        wasEventProcessed = true;
        break;
        
      default:
    }

    return wasEventProcessed;
  }

  @Override
  public boolean timerYellow()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
 
      case lowNorthSouthYellow:
        setStatus(Status.lowNorthSouthRed);
        wasEventProcessed = true;
        break;
      case lowWestEastYellow:
        setStatus(Status.lowNorthSouthArrow);
        wasEventProcessed = true;
        break;

      case modNorthYellow:
        setStatus(Status.modSouthArrowGreen);
        wasEventProcessed = true;
        break;
      case modSouthYellow:
        setStatus(Status.modWestEastGreen);
        wasEventProcessed = true;
        break;
      case modWestEastYellow:
        setStatus(Status.modNorthArrowGreen);
        wasEventProcessed = true;
        break;

      case highNorthYellow:
        setStatus(Status.highSouthArrowGreen);
        wasEventProcessed = true;
        break;
      case highSouthYellow:
        setStatus(Status.highWestArrowGreen);
        wasEventProcessed = true;
        break;
      case highWestEastYellow:
        setStatus(Status.highNorthArrowGreen);
        wasEventProcessed = true;
        break;
        
      default:
    }

    return wasEventProcessed;
  }

  @Override
  public boolean lowTraffic()
  {

    setStatus(Status.lowNorthSouthArrow);
    return true;
  }

  @Override
  public boolean moderateTraffic()
  {
    setStatus(Status.modNorthArrowGreen);
    return true;
  }

  @Override
  public boolean highTraffic()
  {
    setStatus(Status.highNorthArrowGreen);
    return true;
  }

  //------------------------
  // STATE ENTRY ACTIONS
  //------------------------

  private void setStatus(Status aStatus)
  {
    status = aStatus;

    switch(status)
    {

      case lowNorthSouthArrow:
        trafficLightManager.northArrow();
        trafficLightManager.southArrow();
        trafficLightManager.westRed();
        trafficLightManager.eastRed();
        break;
        
      case lowNorthSouthGreen:
        trafficLightManager.northGreen();
        trafficLightManager.southGreen();
        trafficLightManager.westRed();
        trafficLightManager.eastRed();
        break;
        
      case lowNorthSouthYellow:
        trafficLightManager.northYellow();
        trafficLightManager.southYellow();
        trafficLightManager.westRed();
        trafficLightManager.eastRed();
        break;
        
      case lowNorthSouthRed:
        trafficLightManager.northRed();
        trafficLightManager.southRed();
        trafficLightManager.westGreen();
        trafficLightManager.eastGreen();
        break;
        
      case lowWestEastYellow:
        trafficLightManager.northRed();
        trafficLightManager.southRed();
        trafficLightManager.westYellow();
        trafficLightManager.eastYellow();
        break;

      case modNorthArrowGreen:
        trafficLightManager.northGreenAndArrow();
        trafficLightManager.southRed();
        trafficLightManager.westRed();
        trafficLightManager.eastRed();
        break;
        
      case modNorthYellow:
        trafficLightManager.northYellow();
        trafficLightManager.southRed();
        trafficLightManager.westRed();
        trafficLightManager.eastRed();
        break;
        
      case modSouthArrowGreen:
        trafficLightManager.northRed();
        trafficLightManager.southGreenAndArrow();
        trafficLightManager.westRed();
        trafficLightManager.eastRed();
        break;
        
      case modSouthYellow:
        trafficLightManager.northRed();
        trafficLightManager.southYellow();
        trafficLightManager.westRed();
        trafficLightManager.eastRed();
        break;
        
      case modWestEastGreen:
        trafficLightManager.northRed();
        trafficLightManager.southRed();
        trafficLightManager.westGreen();
        trafficLightManager.eastGreen();
        break;
        
      case modWestEastYellow:
        trafficLightManager.northRed();
        trafficLightManager.southRed();
        trafficLightManager.westYellow();
        trafficLightManager.eastYellow();
        break;

      case highNorthArrowGreen:
        trafficLightManager.northGreenAndArrow();
        trafficLightManager.southRed();
        trafficLightManager.westRed();
        trafficLightManager.eastRed();
        break;
        
      case highNorthYellow:
        trafficLightManager.northYellow();
        trafficLightManager.southRed();
        trafficLightManager.westRed();
        trafficLightManager.eastRed();
        break;
        
      case highSouthArrowGreen:
        trafficLightManager.northRed();
        trafficLightManager.southGreenAndArrow();
        trafficLightManager.westRed();
        trafficLightManager.eastRed();
        break;
        
      case highSouthYellow:
        trafficLightManager.northRed();
        trafficLightManager.southYellow();
        trafficLightManager.westRed();
        trafficLightManager.eastRed();
        break;
        
      case highWestArrowGreen:
        trafficLightManager.northRed();
        trafficLightManager.southRed();
        trafficLightManager.westGreenAndArrow();
        trafficLightManager.eastRed();
        break;
        
      case highWestEastGreen:
        trafficLightManager.northRed();
        trafficLightManager.southRed();
        trafficLightManager.westGreen();
        trafficLightManager.eastGreen();
        break;
        
      case highWestEastYellow:
        trafficLightManager.northRed();
        trafficLightManager.southRed();
        trafficLightManager.westYellow();
        trafficLightManager.eastYellow();
        break;
    }
  }

  public void delete()
  {
  }
}