import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class recursive {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		ArrayList<Node> aDep = new ArrayList<Node>();
		ArrayList<Node> bDep = new ArrayList<Node>();
		ArrayList<Node> cDep = new ArrayList<Node>();
		ArrayList<Node> dDep = new ArrayList<Node>();
		ArrayList<Node> eDep = new ArrayList<Node>();
		ArrayList<Node> fDep = new ArrayList<Node>();
		ArrayList<Node> allNodes = new ArrayList<Node>();
		
		
		Node A = new Node("a",1);
		
		Node B = new Node();
		B.setHead(false);
		B.setName("b");
		B.setDuration(2456);
		Node C = new Node();
		C.setHead(false);
		C.setName("c");
		C.setDuration(3);
		Node D = new Node();
		D.setHead(false);
		D.setName("d");
		D.setDuration(4);
		Node E = new Node();
		E.setHead(false);
		E.setName("e");
		E.setDuration(5);
		Node F = new Node();
		F.setHead(false);
		F.setName("f");
		F.setDuration(6);
		
		/*
		aDep.add(B);
		aAncest.add(C);
		A.setAncestors(aAncest);
		
		
		bAncest.add(F);
		B.setAncestors(bAncest);
		
		cAncest.add(D);
		C.setAncestors(cAncest);
		
		dAncest.add(E);
		D.setAncestors(dAncest);
		
		E.setAncestors(eAncest);
		
		fAncest.add(E);
		F.setAncestors(fAncest);
		*/
		
		bDep.add(A);
		//bDep.add(C);
		//dDep.add(B);
		cDep.add(B);
		dDep.add(C);
		eDep.add(D);
		//eDep.add(C);
		fDep.add(E);

		
		//A.setDependencies(aDep);
		B.setDependencies(bDep);
		C.setDependencies(cDep);
		D.setDependencies(dDep);
		E.setDependencies(eDep);
		F.setDependencies(fDep);
		
		
		
		
		allNodes.add(B);
		allNodes.add(D);
		allNodes.add(F);
		allNodes.add(A);
		allNodes.add(C);
		allNodes.add(E);
		
		NodeList list = NodeList.getInstance();
		list.setNodeList(allNodes);
		Organizer thing = new Organizer(list);
		//thing.checkAll();
		thing.recursiveStartPath();
		thing.preparePathList();
		
		
		ReportCreator report = new ReportCreator(thing);
		try {
			report.createReport("test");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not create output file");
		}
		
		
		
		/*
		ArrayList<PathData> pathList = thing.getPathList();
		assertTrue(pathList.size()==2);
		*/
	}

}
