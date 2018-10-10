import static org.junit.jupiter.api.Assertions.*;

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
		
		
		Node A = new Node("a",5);
		
		Node B = new Node();
		B.setHead(false);
		B.setName("b");
		Node C = new Node();
		C.setHead(false);
		C.setName("c");
		Node D = new Node();
		D.setHead(false);
		D.setName("d");
		Node E = new Node();
		E.setHead(false);
		E.setName("e");
		Node F = new Node();
		F.setHead(false);
		F.setName("f");
		
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
		bDep.add(C);
		dDep.add(B);
		cDep.add(D);
		eDep.add(B);
		eDep.add(C);
		fDep.add(D);

		
		A.setDependencies(aDep);
		B.setDependencies(bDep);
		C.setDependencies(cDep);
		D.setDependencies(dDep);
		E.setDependencies(eDep);
		F.setDependencies(fDep);
		
		
		
		allNodes.add(A);
		allNodes.add(B);
		allNodes.add(C);
		allNodes.add(D);
		allNodes.add(E);
		allNodes.add(F);
		
		NodeList list = NodeList.getInstance();
		list.setNodeList(allNodes);
		Organizer thing = new Organizer(list);
		
		thing.checkForCycle();
		
		System.out.println(thing.isValid());
		System.out.println(thing.getErrorCode());
		
		
		
		/*
		ArrayList<PathData> pathList = thing.getPathList();
		assertTrue(pathList.size()==2);
		*/
	}

}
