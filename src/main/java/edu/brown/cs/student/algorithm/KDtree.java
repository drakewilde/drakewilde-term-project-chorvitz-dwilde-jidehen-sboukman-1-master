package edu.brown.cs.student.algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import edu.brown.cs.student.objects.Placeable;

/**
 * Creates a kd tree used for kth dimentional search algorithm.
 *
 * @author solomonboukman
 *
 * @param <P> class that extends Person
 */

public class KDtree<P extends Placeable> {

  private KDtree<P> left;
  private KDtree<P> right;
  private P root;
  private int depth;
  private List<Placeable> closest;
  private final int numNeighbors = 1;
  private int[] pos;

  /**
   * The constructor for the KDTree builds the entire thing.
   *
   * @param comparator The comparator which will be used to sort nodes
   * @param nodes      The list of nodes to go into the tree
   * @param inputDepth The current depth of this iteration of the tree
   * @param numDim     The number of dimensions this tree has
   */
  public KDtree(KDcomparator<P> comparator, List<P> nodes, int inputDepth, int numDim) {
    depth = inputDepth;
    depth++;
    comparator.setDimension(depth % numDim);
    nodes.sort(comparator);
    pos = new int[2];
    closest = new ArrayList<>();
    if (nodes.size() == 0) {
      this.left = null;
      this.right = null;
    } else {
      int mid = (nodes.size()) / 2;
      this.root = nodes.get(mid);
      root.setDim(depth);
      this.left = new KDtree<P>(comparator, nodes.subList(0, mid), depth, numDim);
      this.right = new KDtree<P>(comparator, nodes.subList(mid + 1, nodes.size()), depth,
          numDim);
    }
  }


  /**
   * Returns the left subtree.
   *
   * @return The left subtree
   */
  public KDtree<P> getLeft() {
    return left;
  }

  /**
   * Returns the right subtree.
   *
   * @return The right subtree
   */
  public KDtree<P> getRight() {
    return right;
  }

  /**
   * Returns the root of the tree or subtree.
   *
   * @return The root of the tree
   */
  public P getRoot() {
    return root;
  }

  /**
   * Sets position.
   * @param aCoord Coordinate.
   */
  public void setPos(Coordinate aCoord) {
    pos[0] = aCoord.getX();
    pos[1] = aCoord.getY();
  }

  /**
   * Searches the tree to update closest list.
   * @param currTree KdTree.
   */
  public void search(KDtree<P> currTree) {
    this.updateClosestList(currTree.getRoot());
    int currDim = currTree.getRoot().getDim() % 2;
    if (closest.size() == 0) {
      if (currTree.getLeft().getRoot() != null) {
        this.search(currTree.getLeft());
      }
      if (currTree.getRight().getRoot() != null) {
        this.search(currTree.getRight());
      }
    }
    if (closest.size() == 0) {
      return;
    }
    if (dist(closest.get(closest.size() - 1)) > Math
        .abs(pos[currDim] - currTree.getRoot().getCoordinates().asList().get(currDim).doubleValue())
        || closest.size() < numNeighbors) {

      if (currTree.getLeft().getRoot() != null) {
        this.search(currTree.getLeft());
      }
      if (currTree.getRight().getRoot() != null) {
        this.search(currTree.getRight());
      }
    } else {
      if (currTree.getRoot().getCoordinates().asList().get(currDim).doubleValue() < pos[currDim]) {
        if (currTree.getRight().getRoot() != null) {
          this.search(currTree.getRight());
        }
      } else if (currTree.getRoot().getCoordinates().asList().get(currDim).doubleValue() > pos[currDim]) {
        if (currTree.getLeft().getRoot() != null) {
          this.search(currTree.getLeft());
        }
      }
    }
  }

  /**
   * This method checks to see whether or not the list need be changed based off a particular location.
   * @param curr Current Placeable.
   */
  private void updateClosestList(Placeable curr) {
    if (pos[0] == curr.getCoordinates().getX().doubleValue()
        && pos[1] == curr.getCoordinates().getY().doubleValue()) {
      return;
    }

    if (closest.size() < numNeighbors) {
      closest.add(curr);
    } else {
      // If dist of curr is < dist of farthest neighbor
      if (dist(curr) < dist(closest.get(closest.size() - 1))) {
        closest.add(curr);
        closest.sort(new CompDist());
        closest.remove(closest.size() - 1);
      }
    }
  }

  /**
   * This comparator takes in two nodes and compares them based off their distance
   * to the target stored inside this NeighborsCmd class.
   *
   * @author Solomon
   *
   */
  private class CompDist implements Comparator<Placeable> {

    /**
     * The compare method compares the two nodes based off distance to a set target.
     */
    @Override
    public int compare(Placeable n1, Placeable n2) {
      int ret = 0;
      if (dist(n1) > dist(n2)) {
        ret = 1;
      } else if (dist(n1) < dist(n2)) {
        ret = -1;
      }
      return ret;
    }
  }

  /**
   * This method returns the distance between the set target and the destination.
   * @param dest Destination placeable.
   * @return Distance between target and destination.
   */
  private double dist(Placeable dest) {
    double dim1 = (dest.getCoordinates().getX().doubleValue() - pos[0]);
    double dim1sq = dim1 * dim1;
    double dim2 = (dest.getCoordinates().getY().doubleValue() - pos[1]);
    double dim2sq = dim2 * dim2;
    return Math.sqrt(dim1sq + dim2sq);
  }

  /**
   * Gets the closest placeable in list.
   * @return Closeste placeable in list.
   */
  public List<Placeable> getClosest() {
    return closest;
  }

}

