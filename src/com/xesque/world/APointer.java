package com.xesque.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class APointer 
{
	public static double lastTime = System.currentTimeMillis();
	private Comparator<Node> nodeSorter = new Comparator<Node>() {
		@Override
		public int compare(Node n0, Node n1)
		{
			if(n1.fCost < n0.fCost)
			{
				return +1;
			}
			if(n1.fCost > n0.fCost)
			{
				return -1;
			}
			return 0;
		}
	};
	
	public static boolean clear()
	{
		if(System.currentTimeMillis() - lastTime >= 1000) 
		{
			return true;
		}
		return false;
	}
	/*
	public List<Node> findPath(World world, Vector2i start, Vector2i end)
	{
		lastTime = System.currentTimeMillis();
		List<Node> openList = new ArrayList<Node>();
		List<Node> closedList = new ArrayList<Node>();
		
		Node current = new Node(start, null, 0, getDistance(start, end));
		openList.add(current);
		while(openList.size() > 0)
		{
			Collections.sort(openList, nodeSorter);
			current = openList.get(0);
			if(current.tile.equals(end))
			{
				List<Node> path = new ArrayList();
				while(current.parent != null)
				{
					path.add(current);
					current = current.parent;
				}
				openList.clear();
				closedList.clear();
				return path;
			}
			
		}
	}
	*/
	private static boolean vecInList(List<Node> list, Vector2i vec)
	{
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).tile.equals(vec))
			{
				return true;
			}
		}
		return false;
	}
	
	private static double getDistance(Vector2i tile, Vector2i goal)
	{
		double dx = tile.x - goal.x;
		double dy = tile.y - goal.y;
		
		return Math.sqrt(dx * dx + dy * dy);
	}
}
