import org.junit.Assert;
import org.junit.Test;
import org.rbrtwlz.edgedetection.ImageArray;
import org.rbrtwlz.edgedetection.filters.Kernel;
import org.rbrtwlz.edgedetection.filters.SlidingWindowFilter;


public class SlidingWindowFilterTest extends SlidingWindowFilter {
    @Test
    public void testArray2dSlice() {
        int[][] array = {{1,2,3,4,5},
                         {2,3,4,5,6},
                         {3,4,5,6,7},
                         {4,5,6,7,8},
                         {5,6,7,8,9}};
        int[] dim0 = {1,4};
        int[] dim1 = {0,2};
        int[][] res = this.array2dSlice(array, dim0, dim1);
        int[][] expected = {{2,3,4}, {3,4,5}};
        for(int i=0; i<res.length; i++){
            Assert.assertArrayEquals("slice not correct", res[i], expected[i]);
        }
    }
    @Test
    public void testArrayWeightedSum(){
        int[][] arr1 = {{1,1,1},{2,2,2},{3,3,3}};
        int[][] arr2 = {{0,0,0}, {1,1,1},{2,2,2}};
        int res = this.arrayWeightedSum(arr1, arr2);
        Assert.assertEquals("weighted sum of arrays not correct", (3*2+3*6), res);
    }
    @Test
    public void testApplyKernel(){
        int[][] k = {{0,0,0},{1,1,1},{0,0,0}};
        int[][] pa_1c = {{1,2,3,4,5},
                         {2,3,4,5,6},
                         {3,4,5,6,7},
                         {4,5,6,7,8},
                         {5,6,7,8,9}};
        int [][][] pa = {pa_1c, pa_1c, pa_1c};
        int[][] res_1c = this.applyKernel(pa, new Kernel(k))[0];
        Assert.assertEquals("result width not correct", 3, res_1c[0].length);
        Assert.assertEquals("result height not correct", 3, res_1c.length);
        int[][] expected = {{9,12,15},
                            {12,15,18},
                            {15,18,21}};
        for(int i=0; i<res_1c.length; i++){
            Assert.assertArrayEquals("result applying kernel not correct", res_1c[i], expected[i]);
        }
    }

    @Override
    public ImageArray applyFilter(ImageArray imageArray) {
        return null;
    }
}
